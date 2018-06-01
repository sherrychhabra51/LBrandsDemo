package lbrands.com.ui.repo.repolist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lbrands.com.data.model.RepoResponseModel;
import lbrands.com.lbrandsassignment.R;
import lbrands.com.lbrandsassignment.databinding.RepoItemBinding;

public class RepoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_DATA = 0;
    public static final int TYPE_FOOTER = 1;

    private List<RepoResponseModel> listRepos;
    private RepoCallback repoCallback;
    private boolean isFooterEnabled = true;
    RepoListAdapter(RepoCallback repoCallback) {
        this.repoCallback = repoCallback;
    }


    public void setRepoList(final List<RepoResponseModel> listRepos) {
        if (this.listRepos == null) {
            this.listRepos = listRepos;
            notifyItemRangeInserted(0, listRepos.size());
        } else {
            this.listRepos = listRepos;
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemViewType(int position) {
        return (isFooterEnabled && position >= listRepos.size() ) ? TYPE_FOOTER : TYPE_DATA;
    }
    @Override
    public int getItemCount() {
        if(listRepos==null || listRepos.size()==0){
            isFooterEnabled = false;
        }
        return isFooterEnabled ? listRepos.size() + 1 : listRepos!=null? listRepos.size():0;
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_DATA) {
            RepoItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_repo, parent, false);
            return new RepoHolder(binding);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo_footer, parent, false);
            return new FooterHolder(view);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_DATA) {
            RepoHolder repoHolder = (RepoHolder) holder;
            repoHolder.binding.setRepo(listRepos.get(position));
            repoHolder.binding.setRepoCallback(repoCallback);
            repoHolder.binding.executePendingBindings();
        } else {
            ((FooterHolder) holder).progressBar.setIndeterminate(true);
        }

    }

    static class RepoHolder extends RecyclerView.ViewHolder {

        final RepoItemBinding binding;

        RepoHolder(RepoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static class FooterHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public FooterHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
