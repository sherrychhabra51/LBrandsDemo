package lbrands.com.ui.login;

public interface LoginNavigator {

    void doLogin();
    void goToRepoListActivity();
    void onError(String errorMsg);
}
