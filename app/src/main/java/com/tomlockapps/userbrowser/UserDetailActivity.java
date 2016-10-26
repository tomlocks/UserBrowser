package com.tomlockapps.userbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.tomlockapps.userbrowser.picasso.CircleTransform;
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

    public static void startActivity(Activity activity, View imageView,IUserModel userModel) {
        Intent i = new Intent(activity, UserDetailActivity.class);
        i.putExtra(EXTRA_USER, userModel);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, (View)imageView, "profile");

        activity.startActivity(i, options.toBundle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showUserDetail(UserViewModel userViewModel) {
        nameView.setText(userViewModel.getName());
        nameView.setTextColor(getResources().getColor(userViewModel.getBackgroundColorResId()));
        Picasso.with(getApplicationContext()).load(userViewModel.getAvatarUrl()).transform(new CircleTransform(getResources().getColor(userViewModel.getBackgroundColorResId()))).into(avatarView);

     /*   Picasso.with(this)
                .load(userViewModel.getAvatarUrl())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(avatarView);*/
    }
}
