package lbrands.com.ui.repo;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import lbrands.com.data.Repository;

public class RepoFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository repository;
    private final Application application;

    RepoFactory(Application application, Repository repository) {
        this.repository = repository;
        this.application = application;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new RepoViewModel(application, repository);
    }
}
