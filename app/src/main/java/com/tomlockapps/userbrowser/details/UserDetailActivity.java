package com.tomlockapps.userbrowser.details;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.tomlockapps.userbrowser.R;
import com.tomlockapps.userbrowser.data.IUserModel;
import com.tomlockapps.userbrowser.databinding.ActivityUserDetailBinding;

public class UserDetailActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "UserDetailActivity.User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUserDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail);

        Bundle extras = getIntent().getExtras();
        IUserModel userModel = extras.getParcelable(EXTRA_USER);

        UserDetailsViewModel viewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class);
        viewModel.setUserModel(userModel);

        binding.setViewmodel(viewModel);

        final View sourceTextView = findViewById(R.id.user_detail_source);
        final View nameTextView = findViewById(R.id.user_detail_name);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {

                @Override
                public void onTransitionStart(Transition transition) {
                    sourceTextView.setVisibility(View.INVISIBLE);
                    nameTextView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    AlphaAnimation animation = new AlphaAnimation(0f, 1f);
                    animation.setDuration(600);

                    nameTextView.startAnimation(animation);
                    sourceTextView.startAnimation(animation);
                    nameTextView.setVisibility(View.VISIBLE);
                    sourceTextView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }


    public static void startActivity(Activity activity, View imageView, IUserModel userModel) {
        Intent i = new Intent(activity, UserDetailActivity.class);
        i.putExtra(EXTRA_USER, userModel);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, imageView, "profile");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivityForResult(i, 1234, options.toBundle());
        } else
            activity.startActivity(i);
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

}
