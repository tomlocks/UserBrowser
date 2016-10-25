package com.tomlockapps.userbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tomlockapps.userbrowser.sources.GithubSource;
import com.tomlockapps.userbrowser.sources.github.GithubService;
import com.tomlockapps.userbrowser.sources.model.GithubUserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.main_frame ,UsersFragment.newInstance(), null).commit();
        }
    }
}
