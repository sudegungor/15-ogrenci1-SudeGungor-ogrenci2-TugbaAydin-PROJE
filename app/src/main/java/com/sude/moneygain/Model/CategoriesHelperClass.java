package com.sude.moneygain.Model;

import android.graphics.drawable.GradientDrawable;

public class CategoriesHelperClass {

    int image;
    String title;
    GradientDrawable gradientDrawable;

    String username_email, pass;

    public CategoriesHelperClass(GradientDrawable gradientDrawable, int image, String title,String username_email, String pass) {
        this.image = image;
        this.title = title;
        this.gradientDrawable = gradientDrawable;
        this.username_email = username_email;
        this.pass = pass;
    }

    public GradientDrawable getGradientDrawable() {
        return gradientDrawable;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername_email() { return username_email;}

    public String getPass() {return pass;}
}
