package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInScreen extends AppCompatActivity {

    private EditText email;
    private EditText password;
    boolean result = false;
    DatabaseReference databaseReference;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            this.show_toast("Click BACK again to exit!");

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
        setContentView(R.layout.activity_sign_in_screen);

        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);

    }

    public void call_signup(View view) {
        Intent intent = new Intent(SignInScreen.this, SignUpScreen.class);
        startActivity(intent);
    }

    public void call_login(View view) {


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(email.getText().toString())) {
                    if (password.getText().toString().equals(dataSnapshot.child(email.getText().toString()).child("user_Password").getValue())) {
                        setDefaults("username",email.getText().toString(),SignInScreen.this);
                        Toast.makeText(SignInScreen.this, "Successfully Loggedin", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInScreen.this,HomePageTabScreen.class));
                    } else {
                        Toast.makeText(SignInScreen.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignInScreen.this, "Incorrect User Name", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void show_toast(String string)
    {
        Toast toast = Toast.makeText(SignInScreen.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }

    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

}
