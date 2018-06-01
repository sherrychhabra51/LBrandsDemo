package lbrands.com.ui.repo.repolist;

import lbrands.com.data.model.RepoResponseModel;

public interface RepoCallback {

    void onRepoURLClick(RepoResponseModel repoResponseModel);

    void onRepoItemCLick(RepoResponseModel repoResponseModel);
}
