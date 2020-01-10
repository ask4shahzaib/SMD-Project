package com.example.projectphase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class
RegistrationScreen extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            this.show_toast("press BACK again to exit!");

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
    }

    public void call_r_signup(View view)
    {
        if(this.isConnectedToInternet()) {
            Intent intent = new Intent(RegistrationScreen.this, SignUpScreen.class);
            startActivity(intent);
        }
        else
        {
            this.show_toast("Internet not connected");
        }
    }

    public void call_r_signin(View view)
    {
        if(this.isConnectedToInternet()) {
            Intent intent = new Intent(RegistrationScreen.this, SignInScreen.class);
            startActivity(intent);
        }
        else
        {
            this.show_toast("Internet not connected");
        }
    }

    public void call_google(View view)
    {
        if(this.isConnectedToInternet()) {
            Intent intent = new Intent(RegistrationScreen.this, HomePageTabScreen.class);
            startActivity(intent);
        }
        else
        {
            this.show_toast("Internet not connected");
        }
    }


    public boolean isConnectedToInternet(){
        boolean have_WIFI = false;
        boolean have_MData = false;

        ConnectivityManager connectivityManager =(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for(NetworkInfo info:networkInfos)
        {
            if(info.getTypeName().equalsIgnoreCase("WIFI"))
                if(info.isConnected())
                    have_WIFI=true;
            if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                if(info.isConnected())
                    have_MData=true;
        }
        return have_MData||have_WIFI;
    }

    public void show_toast(String string)
    {
        Toast toast = Toast.makeText(RegistrationScreen.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }
}
