package com.example.projectphase1.ui.main;

public class LoginPresenter implements LoginInteractor.onLoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;
    LoginPresenter(LoginView loginView ,LoginInteractor loginInteractor)
    {
    this.loginView=loginView;
    this.loginInteractor=loginInteractor;
    }
    public void validateCredentials(String username , String password)
    {
    if(loginView != null){
        loginView.showProgress();
    }

    loginInteractor.login(username,password,this);
    }

    public void onDestroy()
    {
    loginView=null;
    }

    @Override
    public void onUsernameError() {
    if(loginView != null)
    {
        loginView.setUsernameError();
        loginView.hideProgress();
    }

    }

    @Override
    public void onPasswordError() {
    loginView.setPasswordError();
    loginView.hideProgress();
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
