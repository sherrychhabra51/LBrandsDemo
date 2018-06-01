package lbrands.com.data.database;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.model.RepoResponseModel;

public class AppDBManager implements DBManager {
    private AppDatabase appDatabase;

    public AppDBManager(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> saveLoginDetails(LoginResponseModel loginResponseModel) {
        return Observable.fromCallable(() -> {
            appDatabase.loginDAO().insertUserDetail(loginResponseModel);
            return true;
        });

    }

    @Override
    public Observable<LoginResponseModel> getLoginDetails() {
        return Observable.fromCallable(() -> appDatabase.loginDAO().getUserDetail());

    }

    @Override
    public Observable<Boolean> saveRepoList(ArrayList<RepoResponseModel> listRepo) {
        return Observable.fromCallable(() -> {
            appDatabase.repoDAO().insertAll(listRepo);
            return true;
        });

    }

    @Override
    public LiveData<List<RepoResponseModel>> getRepoList(String name, int pageNo, int userName) {
        return appDatabase.repoDAO().getRepoList();


    }

    @Override
    public Observable<Boolean> clearDB() {
        return Observable.fromCallable(() -> {
            appDatabase.clearAllTables();
            return true;
        });
    }
}
