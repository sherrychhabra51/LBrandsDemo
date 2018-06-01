package lbrands.com.ui.login;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import lbrands.com.data.model.ErrorResponseModel;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.lbrandsassignment.R;
import lbrands.com.lbrandsassignment.databinding.LoginBinding;
import lbrands.com.ui.base.BaseActivity;
import lbrands.com.ui.repo.RepoActivity;
import lbrands.com.utils.DependencyInjectionUtils;

public class LoginActivity extends BaseActivity implements LoginNavigator {

    private LoginViewModel loginViewModel;

    public static Intent intentFor(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        return intent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginFactory factory = new LoginFactory(getApplication(), DependencyInjectionUtils.provideRepository(this));
        loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);
        loginViewModel.setNavigator(this);

        LoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
    }


    @Override
    public void doLogin() {
        hideKeyboard();
        if (loginViewModel.isUsernameValid()) {
            loginViewModel.setIsLoading(true);
            loginViewModel.fetchUserDetails(loginViewModel.userName.getValue()).observe(this, observer);
        } else {
            showSnackbar(getString(R.string.error_username));
        }
    }

    private Observer<LoginResponseModel> observer = loginResponseModel -> {
        if (loginResponseModel != null) {
            validateResponse(loginResponseModel);
        }
    };

    private void validateResponse(LoginResponseModel loginResponseModel) {
        loginViewModel.setIsLoading(false);
        ErrorResponseModel errorResponseModel = loginResponseModel.getErrorResponseModel();
        if (errorResponseModel != null && errorResponseModel.getMessage() != null) {
            loginViewModel.liveLoginResponse = null;
            showSnackbar(errorResponseModel.getMessage());
        } else {
            loginViewModel.saveLoginDetails(loginResponseModel).observe(this, aBoolean -> goToRepoListActivity());
        }
    }

    @Override
    public void goToRepoListActivity() {
        String userName = loginViewModel.userName.getValue();
        Intent intent = RepoActivity.intentFor(this, userName);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(String errorMsg) {
        showSnackbar(errorMsg);
    }
}
