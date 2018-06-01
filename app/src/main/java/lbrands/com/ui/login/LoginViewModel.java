package lbrands.com.ui.login;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lbrands.com.data.Repository;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.ui.base.BaseViewModel;
import lbrands.com.utils.ValidationUtils;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    public final MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<LoginResponseModel> liveLoginResponse = null;

    LoginViewModel(@NonNull Application application, Repository repository) {
        super(application, repository);
    }

    public boolean isUsernameValid() {
        return ValidationUtils.isInputValid(userName.getValue());
    }

    public void onLoginClick() {
        getNavigator().doLogin();
    }

    public MutableLiveData<LoginResponseModel> fetchUserDetails(String userName) {
        if (liveLoginResponse == null) {
            liveLoginResponse = getRepository().getUserDetails(userName);
        }
        return liveLoginResponse;
    }

    public MutableLiveData<Boolean> saveLoginDetails(LoginResponseModel loginResponseModel) {
        MutableLiveData<Boolean> isDataSaved = new MutableLiveData<>();
        Disposable d = getRepository().saveLoginDetails(loginResponseModel)
                .subscribeOn(Schedulers.io())
                .subscribe(aBoolean -> {
                    Log.d("isData Saved", String.valueOf(aBoolean));
                    isDataSaved.postValue(aBoolean);
                }, throwable -> {
                    Log.e("error Saving", String.valueOf(throwable.getMessage()));
                    isDataSaved.postValue(false);
                });
        return isDataSaved;
    }
}
