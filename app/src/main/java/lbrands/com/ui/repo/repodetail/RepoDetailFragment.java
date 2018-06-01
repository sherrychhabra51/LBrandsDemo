package lbrands.com.ui.repo.repodetail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lbrands.com.data.model.RepoResponseModel;
import lbrands.com.lbrandsassignment.R;
import lbrands.com.lbrandsassignment.databinding.RepoDetailBinding;
import lbrands.com.ui.base.BaseFragment;

public class RepoDetailFragment extends BaseFragment {
    public static final String KEY_REPO_DETAIL = "repoResponseModel";

    public static RepoDetailFragment getInstance(RepoResponseModel repoResponseModel) {
        RepoDetailFragment repoDetailFragment = new RepoDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_REPO_DETAIL, repoResponseModel);
        repoDetailFragment.setArguments(bundle);
        return repoDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RepoResponseModel repoResponseModel = getArguments().getParcelable(KEY_REPO_DETAIL);
        RepoDetailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.fragment_repo_detail, container, false);
        binding.setRepo(repoResponseModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
