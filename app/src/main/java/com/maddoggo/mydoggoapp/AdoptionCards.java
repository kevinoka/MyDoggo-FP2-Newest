package com.maddoggo.mydoggoapp;

import android.widget.ImageView;

public class AdoptionCards {
    private ImageView userId;
    private String name;
    public AdoptionCards (ImageView userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public ImageView getUserId() {
        return userId;
    }

    public void setUserId(ImageView userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
