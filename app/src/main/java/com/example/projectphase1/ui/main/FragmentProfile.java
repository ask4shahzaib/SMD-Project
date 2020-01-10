package com.example.projectphase1.ui.main;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectphase1.ClassProfile;
import com.example.projectphase1.R;
import com.example.projectphase1.RoomDatabaseClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import static android.app.Activity.RESULT_OK;


public class FragmentProfile extends Fragment {

    private static final int CHOOSE_IMAGE =1 ;
    EditText profile__name;
    EditText profile__number;
    EditText getProfile__Adress;
    TextView username_heading;
    TextView email_heading;
    ImageView imageView;
    String username;
    TextView textViewn;
    EditText em;
    TextView tv;
    Button change;
    ClassProfile classProfile;
    Button save;
    View view;
    DatabaseReference databaseReference;
    //https://www.wallpaperup.com/uploads/wallpapers/2014/09/24/455833/4816bfd02322b6bfa75cf5a86655a68e-700.jpg
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_fragment_profile, container, false);

        imageView=view.findViewById(R.id.profile_back);
        em=view.findViewById(R.id.profile_tv_email);
        email_heading=view.findViewById(R.id.profile_tv_useremail_heading);
        username_heading=view.findViewById(R.id.profile_tv_username_heading);
        tv=view.findViewById(R.id.profile_tv_username);
        save=view.findViewById(R.id.profile_tv_savebtn);
        change=view.findViewById(R.id.profile_tv_changebtn);
        profile__name=view.findViewById(R.id.profile_tv_name);
        profile__number=view.findViewById(R.id.profile_tv_phone);
        textViewn=view.findViewById(R.id.jn);
        getProfile__Adress=view.findViewById(R.id.profile_tv_adress);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change.setVisibility(View.GONE);
                email_heading.setVisibility(View.GONE);
                username_heading.setVisibility(View.GONE);
                tv.setVisibility(View.GONE);
                em.setVisibility(View.GONE);
                profile__name.setEnabled(true);
                profile__number.setEnabled(true);
                getProfile__Adress.setEnabled(true);
                save.setVisibility(View.VISIBLE);
                change.setEnabled(false);
                save.setEnabled(true);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save.setVisibility(View.GONE);
                username_heading.setVisibility(View.VISIBLE);
                tv.setVisibility(View.VISIBLE);
                email_heading.setVisibility(View.VISIBLE);
                em.setVisibility(View.VISIBLE);
                profile__name.setEnabled(false);
                profile__number.setEnabled(false);
                getProfile__Adress.setEnabled(false);
                change.setVisibility(View.VISIBLE);
                change.setEnabled(true);
                save.setEnabled(false);
                classProfile.setProfile_name(profile__name.getText().toString());
                classProfile.setProfile_adress(getProfile__Adress.getText().toString());
                classProfile.setProfile_phone(profile__number.getText().toString());
                databaseReference.child(username).setValue(classProfile);
            }
        });






        getdata();
     //   Toast.makeText(getContext(),"view",Toast.LENGTH_SHORT).show();
        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getdata();

    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
    public void getdata()
    {
        username=getDefaults("username",this.getContext());
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Profile");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                classProfile=new ClassProfile(dataSnapshot.child(username).child("profile_username").getValue()+"",
                        dataSnapshot.child(username).child("profile_name").getValue()+"",
                        dataSnapshot.child(username).child("profile_email").getValue()+"",
                        dataSnapshot.child(username).child("profile_phone").getValue()+"",
                        dataSnapshot.child(username).child("profile_adress").getValue()+"");
              //  Toast.makeText(getContext(),classProfile.getProfile_email(),Toast.LENGTH_SHORT).show();
                em.setText(classProfile.getProfile_email());
                tv.setText(classProfile.getProfile_username());
              //  Toast.makeText(getContext(),"shoe",Toast.LENGTH_SHORT).show();
                profile__number.setText(classProfile.getProfile_phone());
                profile__name.setText(classProfile.getProfile_name());
                textViewn.setText(profile__name.getText());
                getProfile__Adress.setText(classProfile.getProfile_adress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
