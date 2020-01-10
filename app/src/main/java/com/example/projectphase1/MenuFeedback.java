package com.example.projectphase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuFeedback extends AppCompatActivity {


    DatabaseReference databaseReference;
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_feedback);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Feedback");
        text=findViewById(R.id.feedback_et_text);

    }

    public void call_submit(View view)
    {
        if(TextUtils.isEmpty(text.getText()))
        {
            show_toast("Please enter your feedback.");
        }
        else
        {
           ClassFeedbackFB classFeedbackFB=new ClassFeedbackFB(getDefaults("username",MenuFeedback.this),text.getText().toString());
           String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(classFeedbackFB);

            show_toast("Feedback successfully submitted!");
            startActivity(new Intent(MenuFeedback.this,HomePageTabScreen.class));
        }

    }


    public void show_toast(String string)
    {
        Toast toast = Toast.makeText(MenuFeedback.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}