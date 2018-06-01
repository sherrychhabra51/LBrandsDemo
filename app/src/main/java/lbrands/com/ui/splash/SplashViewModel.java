package lbrands.com.ui.splash;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Observable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lbrands.com.data.Repository;
import lbrands.com.data.model.ErrorResponseModel;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.data.network.APIErrorHandler;
import lbrands.com.ui.base.BaseViewModel;

public class SplashViewModel extends BaseViewModel {

    SplashViewModel(@NonNull Application application, Repository repository) {
        super(application, repository);
    }

    public MutableLiveData<LoginResponseModel> isUserLoggedIn() {
        MutableLiveData<LoginResponseModel> liveLoginResponse = new MutableLiveData<>();
        Disposable d = getRepository().getLoginDetails()
                .subscribeOn(Schedulers.io())
                .subscribe(loginResponseModel -> {
                    liveLoginResponse.postValue(loginResponseModel);
                }, throwable -> {
                    Log.e("Excepion", throwable.getMessage());
                    LoginResponseModel loginResponseModel = new LoginResponseModel();
                    ErrorResponseModel errorResponseModel = new ErrorResponseModel();
                    errorResponseModel.setMessage("Error");
                    liveLoginResponse.postValue(loginResponseModel);
                });
        return liveLoginResponse;
    }

}
