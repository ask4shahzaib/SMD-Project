package com.example.projectphase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Entity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpScreen extends AppCompatActivity {

    public static RoomDatabaseClass roomDatabaseClass;
    private EditText name;
    private EditText user_name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private CheckBox termsNconditions;
    DatabaseReference databaseReference;
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomDatabaseClass= Room.databaseBuilder(getApplicationContext(),RoomDatabaseClass.class,"profile").allowMainThreadQueries().build();
        setContentView(R.layout.activity_sign_up_screen);

        //firebase database
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        db=FirebaseDatabase.getInstance().getReference().child("Profile");
        name=findViewById(R.id.et_signup_name);
        user_name=findViewById(R.id.username);
        email=findViewById(R.id.et_signup_email);
        confirmPassword=findViewById(R.id.et_signup_cpassword);
        password=findViewById(R.id.et_signup_password);
        termsNconditions=findViewById(R.id.checkBox_terms);
    }


    public void call_login1(View view)
    {
        if( TextUtils.isEmpty(name.getText())){
            name.setError( "Name is required!" );
            this.show_toast("Please enter your name");
        }

        else{
            if( TextUtils.isEmpty(email.getText())) {
                email.setError( "Email is required!" );
                this.show_toast("Please enter your email address!");
            }


            else {
                String email1 = email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email1.matches(emailPattern)) {

                    if(TextUtils.isEmpty(user_name.getText())){
                        name.setError("User name is required!");
                        this.show_toast("Please enter user name!");
                    }
                    else {


                        if (TextUtils.isEmpty((password.getText()))) {
                            this.show_toast("Enter your password!");
                        } else if (password.getText().length() < 8) {
                            this.show_toast("Password should be minimum 8 characters long");
                        } else {

                            if (TextUtils.isEmpty(confirmPassword.getText())) {
                                this.show_toast("Please confirm your password!");
                            } else {
                                if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
                                    this.show_toast("Password did not match!");
                                } else {
                                    if (termsNconditions.isChecked()) {

                                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                        databaseReference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.hasChild(user_name.getText().toString()))
                                                {
                                                    show_toast("User exists. Login Login Now!");
                                                }
                                                else
                                                {
                                                    User user = new User();
                                                    user.setUser_Name(name.getText().toString());
                                                    user.setUser_Email(email.getText().toString());
                                                    user.setUser_Password(password.getText().toString());
                                                    user.setUser_UserName(user_name.getText().toString());

                                                    databaseReference.child(user_name.getText().toString()).setValue(user);

                                                    UserEntity userEntity=new UserEntity(name.getText().toString(),email.getText().toString(),"phone number","Adress");
                                                    roomDatabaseClass.myDao1().addProfileEntity(userEntity);

                                                    db.child(user_name.getText().toString()).setValue(new ClassProfile(user_name.getText().toString(),name.getText().toString(),email.getText().toString(),"phone number","Adress"));
                                                    Toast.makeText(SignUpScreen.this,"ACCOUNT CREATED SUCCESSFULLY!!LOGIN NOW",Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
                                                    startActivity(intent);


                                                    email.getText().clear();
                                                    name.getText().clear();
                                                    password.getText().clear();
                                                    confirmPassword.getText().clear();
                                                    termsNconditions.toggle();
                                                    name.setCursorVisible(false);
                                                    email.setCursorVisible(false);
                                                    password.setCursorVisible(false);
                                                    confirmPassword.setCursorVisible(false);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });



                                    } else {
                                        this.show_toast("Please agree to Terms and Conditions!");
                                    }
                                }
                            }
                        }

                    }
                }
                else {
                    this.show_toast("Please enter a valid email address!");
                }
            }
        }

    }

    public void call_signin(View view)
    {
        Intent intent = new Intent(SignUpScreen.this, SignInScreen.class);
        startActivity(intent);
    }

    public void show_toast(String string)
    {
        Toast toast = Toast.makeText(SignUpScreen.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }
}