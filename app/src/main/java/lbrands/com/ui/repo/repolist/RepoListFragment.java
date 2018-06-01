package lbrands.com.ui.repo.repolist;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lbrands.com.data.model.RepoResponseModel;
import lbrands.com.lbrandsassignment.R;
import lbrands.com.lbrandsassignment.databinding.RepoListBinding;
import lbrands.com.ui.base.BaseFragment;
import lbrands.com.ui.repo.repoweb.RepoWebViewActivity;
import lbrands.com.utils.DependencyInjectionUtils;

import static lbrands.com.ui.repo.RepoActivity.KEY_USERNAME;

public class RepoListFragment extends BaseFragment {

    private String userName;
    private static final int PER_PAGE_SIZE = 20;
    private int previousTotal = 0;
    private boolean loading = true;
    private int pageNo = 1;

    public static RepoListFragment getInstance(String userName) {
        RepoListFragment repoListFragment = new RepoListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_USERNAME, userName);
        repoListFragment.setArguments(bundle);
        return repoListFragment;
    }

    private RepoListViewModel repoViewModel;
    private RepoListBinding binding;
    private RepoListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        userName = getArguments().getString(KEY_USERNAME);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.fragment_repo_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RepoListFactory factory = new RepoListFactory(mActivity.getApplication(), DependencyInjectionUtils.provideRepository(mActivity));
        repoViewModel = ViewModelProviders.of(this, factory).get(RepoListViewModel.class);
        binding.setRepoViewModel(repoViewModel);
        binding.setLifecycleOwner(this);

        adapter = new RepoListAdapter(repoClickCallback);
        binding.listRepo.setAdapter(adapter);
        binding.listRepo.addOnScrollListener(recyclerViewOnScrollListener);

        repoViewModel.setIsLoading(true);
        fetchRepoList(userName, pageNo);
    }

    private void fetchRepoList(String userName, int pageNo) {
        repoViewModel.fetchRepoList(userName, pageNo, PER_PAGE_SIZE).observe(this, list -> {
            if (list != null) {
                validateResponse(list);
            }
        });
    }


    private void validateResponse(List<RepoResponseModel> list) {
        if (list.size() > 0) {
            repoViewModel.setIsLoading(false);
            pageNo = (list.size()/PER_PAGE_SIZE)+1;
            Log.e(" page,size",pageNo+" , "+list.size());
            repoViewModel.isRepoAvailable.setValue(true);
            adapter.setRepoList(list);
        }
    }


    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleThreshold = 5;
            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                loading = true;
                repoViewModel.loadMore(userName, pageNo,PER_PAGE_SIZE);
            }
        }
    };

    private final RepoCallback repoClickCallback = new RepoCallback() {

        @Override
        public void onRepoURLClick(RepoResponseModel repoResponseModel) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                Intent intent = RepoWebViewActivity.intentFor(mActivity, repoResponseModel.html_url);
                startActivity(intent);
            }
        }

        @Override
        public void onRepoItemCLick(RepoResponseModel repoResponseModel) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                mActivity.showRepoDetailFragment(repoResponseModel);
            }
        }
    };
}
