package com.tomlockapps.userbrowser.viewmodel;

import com.tomlockapps.userbrowser.R;

/**
 * Enum class that contains all the colors that are used on cards.
 *
 * Created by tomlo on 26.10.2016.
 */

public enum UserColor {
    BLUE(R.color.userColorBlue), LIME(R.color.userColorLime);

    UserColor(int colorResId) {
        this.colorResId = colorResId;
    }

    private int colorResId;

    public int getColorResId() {
        return colorResId;
    }
}
