package lbrands.com.ui.repo;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import lbrands.com.data.database.AppDatabase;
import lbrands.com.data.model.RepoResponseModel;
import lbrands.com.lbrandsassignment.R;
import lbrands.com.ui.base.BaseActivity;
import lbrands.com.ui.login.LoginActivity;
import lbrands.com.ui.repo.repodetail.RepoDetailFragment;
import lbrands.com.ui.repo.repolist.RepoListFragment;
import lbrands.com.utils.DependencyInjectionUtils;

public class RepoActivity extends BaseActivity {
    public static final String KEY_USERNAME = "username";

    public static Intent intentFor(Context mContext, String userName) {
        Intent intent = new Intent(mContext, RepoActivity.class);
        intent.putExtra(KEY_USERNAME, userName);
        return intent;

    }

    private RepoViewModel repoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        RepoFactory factory = new RepoFactory(getApplication(), DependencyInjectionUtils.provideRepository(this));
        repoViewModel = ViewModelProviders.of(this, factory).get(RepoViewModel.class);

        setToolbar();
        showRepoListFragment(getIntent().getStringExtra(KEY_USERNAME));
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void showRepoListFragment(String username) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layoutContainer, RepoListFragment.getInstance(username));
        fragmentTransaction.commit();
    }

    public void showRepoDetailFragment(RepoResponseModel repoResponseModel) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        String tag = RepoDetailFragment.class.getName();
        fragmentTransaction.replace(R.id.layoutContainer, RepoDetailFragment.getInstance(repoResponseModel), tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                showLogoutAlert();
                return true;
        }
        return false;
    }

    private void showLogoutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.lbl_logout))
                .setMessage(getString(R.string.logout_message))
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    clearDB();
                })
                .setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void clearDB() {
        repoViewModel.clearDB().observe(this, aBoolean -> {
            if (aBoolean != null) {
                navigateToLoginScreen();
            }
        });
    }

    private void navigateToLoginScreen() {
        Intent intent = LoginActivity.intentFor(this);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
