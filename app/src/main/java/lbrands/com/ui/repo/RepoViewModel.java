package lbrands.com.ui.repo;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lbrands.com.data.Repository;
import lbrands.com.ui.base.BaseViewModel;
import lbrands.com.ui.login.LoginNavigator;

public class RepoViewModel extends BaseViewModel<LoginNavigator> {

     RepoViewModel(@NonNull Application application, Repository repository) {
        super(application, repository);
    }

    public MutableLiveData<Boolean> clearDB() {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        Disposable d = getRepository().clearDB().subscribeOn(Schedulers.io()).subscribe(aBoolean -> {
            liveData.postValue(aBoolean);
        }, throwable -> {
            liveData.postValue(false);
        });
        return liveData;
    }
}
