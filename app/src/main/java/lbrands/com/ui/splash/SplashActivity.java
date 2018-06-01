package lbrands.com.ui.splash;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;

import lbrands.com.data.model.ErrorResponseModel;
import lbrands.com.data.model.LoginResponseModel;
import lbrands.com.lbrandsassignment.R;
import lbrands.com.ui.base.BaseActivity;
import lbrands.com.ui.login.LoginActivity;
import lbrands.com.ui.repo.RepoActivity;
import lbrands.com.utils.DependencyInjectionUtils;

public class SplashActivity extends BaseActivity {
    private static final int SPLASH_TIME_OUT = 2000;
    private SplashViewModel viewModel;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        SplashFactory factory = new SplashFactory(getApplication(), DependencyInjectionUtils.provideRepository(this));
        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel.class);
        DataBindingUtil.setContentView(this, R.layout.activity_splash);
        navigateTo();
    }

    private void navigateTo() {
        handler.postDelayed(() -> viewModel.isUserLoggedIn().observe(SplashActivity.this, observer), SPLASH_TIME_OUT);
    }

    private Observer<LoginResponseModel> observer = loginResponseModel -> {
        if (loginResponseModel != null) {
            if (loginResponseModel.getLogin() != null && loginResponseModel.getLogin().trim().length() > 0) {
                Intent intent = RepoActivity.intentFor(SplashActivity.this, loginResponseModel.getLogin());
                startActivity(intent);
            } else {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(this::onDestroy);
    }
}