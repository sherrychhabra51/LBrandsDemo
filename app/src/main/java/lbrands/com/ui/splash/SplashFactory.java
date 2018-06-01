package lbrands.com.ui.splash;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import lbrands.com.data.Repository;
import lbrands.com.ui.login.LoginViewModel;

public class SplashFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository repository;
    private final Application application;

    public SplashFactory(Application application, Repository repository) {
        this.repository = repository;
        this.application = application;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SplashViewModel(application, repository);
    }
}
