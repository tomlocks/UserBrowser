package com.tomlockapps.userbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomlockapps.userbrowser.presenter.IUserPresenter;
import com.tomlockapps.userbrowser.presenter.UserPresenter;
import com.tomlockapps.userbrowser.view.IUserView;
import com.tomlockapps.userbrowser.viewmodel.IUserModel;
import com.tomlockapps.userbrowser.viewmodel.UserViewModel;

public class UserDetailActivity extends AppCompatActivity implements IUserView {

    private static final String EXTRA_USER = "UserDetailActivity.User";

    //todo picasso - ladowanie obrazkow - progress lub stub
    //todo strzalka back u gory

    private TextView nameView;
    private ImageView avatarView;

    private IUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        IUserModel userModel = extras.getParcelable(EXTRA_USER);

        nameView = (TextView) findViewById(R.id.user_detail_name);
        avatarView = (ImageView) findViewById(R.id.user_detail_image);

        presenter = new UserPresenter();

        presenter.setView(this);

        presenter.initWithModel(userModel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.init();
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.uninit();
    }

    public static void startActivity(Activity activity, IUserModel userModel) {
        Intent i = new Intent(activity, UserDetailActivity.class);
        i.putExtra(EXTRA_USER, userModel);

        activity.startActivity(i);
    }

    @Override
    public void showUserDetail(UserViewModel userViewModel) {
        nameView.setText(userViewModel.getName());
        nameView.setTextColor(userViewModel.getBackgroundColorResId());
        Picasso.with(getApplicationContext()).load(userViewModel.getAvatarUrl()).into(avatarView);
    }
}
