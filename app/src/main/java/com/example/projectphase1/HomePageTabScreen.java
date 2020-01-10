package com.example.projectphase1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectphase1.ui.main.SectionsPagerAdapter;

public class HomePageTabScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_tab_screen);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons(tabs, toolbar_icons);

        boolean finish = getIntent().getBooleanExtra("finish", false);
        if (finish) {
            startActivity(new Intent(this, RegistrationScreen.class));
            finish();
            return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_feedback) {
            Intent intent = new Intent(this, MenuFeedback.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.menu_translate) {
            Intent intent = new Intent(this, Translator.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.menu_logout)
        {
            Intent intent = new Intent(this, HomePageTabScreen.class);
            intent.putExtra("finish", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
            startActivity(intent);
            finish();
            this.show_toast("Successfully Logged Out!");

        }
        else if(item.getItemId()==R.id.menu_voice_help)
        {
            startActivity(new Intent(HomePageTabScreen.this,voicehelp.class));

        }
        return true;
    }


    private TextView tv;
    final int[] toolbar_icons = new int[]{
            R.drawable.icon_task,
            R.drawable.icon_mytask,
            R.drawable.icon_profile
    };

    private void setupTabIcons(TabLayout tabs, int[] toolbar_icons) {
        for (int i = 0; i < 3; i++)
            tabs.getTabAt(i).setIcon(toolbar_icons[i]);

    }


    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.drawer_menu, menu);
        return true;
    }


    public void call_complain(View view)
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"usamabashir007009@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        this.startActivity(Intent.createChooser(intent, "Send mail"));

    }

    public void show_toast(String string)
    {
        Toast toast = Toast.makeText(HomePageTabScreen.this, string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
        toast.show();
    }
}
