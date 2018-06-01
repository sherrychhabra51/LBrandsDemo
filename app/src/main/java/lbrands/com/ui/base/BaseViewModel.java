package lbrands.com.ui.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import lbrands.com.data.Repository;

public class BaseViewModel<T> extends AndroidViewModel {

    public final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private Repository repository;
    private T mNavigator;

    public BaseViewModel(@NonNull Application application, Repository repository) {
        super(application);
        this.repository = repository;
    }

    public void setIsLoading(boolean isLoad) {
        isLoading.setValue(isLoad);
    }

    public void setNavigator(T mNavigator) {
        this.mNavigator = mNavigator;
    }

    protected T getNavigator() {
        return mNavigator;
    }

    protected Repository getRepository() {
        return repository;
    }


}
