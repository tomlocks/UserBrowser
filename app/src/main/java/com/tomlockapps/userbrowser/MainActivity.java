package com.tomlockapps.userbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.tomlockapps.userbrowser.users.UsersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame , UsersFragment.newInstance(), null).commit();
        }
    }
}
