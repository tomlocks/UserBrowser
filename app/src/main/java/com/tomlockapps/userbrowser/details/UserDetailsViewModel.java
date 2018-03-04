package com.tomlockapps.userbrowser.details;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.tomlockapps.userbrowser.data.IUserModel;

/**
 * Created by walczewski on 04.03.2018.
 */

public class UserDetailsViewModel extends ViewModel {

    public final ObservableField<IUserModel> userModel = new ObservableField<>();

    public UserDetailsViewModel() {}

    public void setUserModel(IUserModel model) {
        userModel.set(model);
    }

    public void onAvatarClick() {
        // todo some future implementation
    }

}
