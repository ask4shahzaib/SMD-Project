package com.example.projectphase1.ui.main;

import android.text.TextUtils;

public class LoginInteractor {
    interface onLoginFinishedListener{
        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }
    public void login(final String username, final String password , final onLoginFinishedListener listener)
    {
        if(TextUtils.isEmpty(username))
        {
            listener.onUsernameError();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            listener.onPasswordError();
            return;
        }
        listener.onSuccess();
    }
}
