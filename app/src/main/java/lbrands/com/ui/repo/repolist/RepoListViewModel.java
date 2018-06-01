package lbrands.com.ui.repo.repolist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lbrands.com.data.Repository;
import lbrands.com.data.model.RepoResponseModel;
import lbrands.com.ui.base.BaseViewModel;
import lbrands.com.ui.login.LoginNavigator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoListViewModel extends BaseViewModel<LoginNavigator> {
    public LiveData<List<RepoResponseModel>> liveData;
    public final MutableLiveData<Boolean> isRepoAvailable = new MutableLiveData<>();

    public boolean shouldFetchData = false;

    RepoListViewModel(@NonNull Application application, Repository repository) {
        super(application, repository);
    }

    public LiveData<List<RepoResponseModel>> fetchRepoList(String userName, int pageNo, int perPageSize) {
        LiveData<List<RepoResponseModel>> liveData = getRepository().getRepoList(userName, pageNo, perPageSize);
        if (liveData == null || liveData.getValue() == null || (liveData.getValue() != null && liveData.getValue().size() == 0))
            loadMore(userName, pageNo, perPageSize);
        return liveData;
    }

    public void loadMore(String userName, int pageNo, int perPageSize) {
        getRepository().fetchUserRepo(userName, pageNo, perPageSize).enqueue(new Callback<ArrayList<RepoResponseModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<RepoResponseModel>> call, @NonNull Response<ArrayList<RepoResponseModel>> response) {
                Log.e(" APi called", "" + pageNo);
                saveRepoList(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<RepoResponseModel>> call, @NonNull Throwable t) {
            }
        });
    }

    private void saveRepoList(ArrayList<RepoResponseModel> list) {
        Disposable d = getRepository().saveRepoList(list).subscribeOn(Schedulers.io()).subscribe(aBoolean -> {
            Log.d("data save", String.valueOf(aBoolean));
        }, throwable -> {
            Log.e("Error", String.valueOf(throwable.getMessage()));
        });
    }
}
