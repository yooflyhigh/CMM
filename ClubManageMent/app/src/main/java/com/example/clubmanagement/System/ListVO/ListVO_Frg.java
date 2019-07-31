package com.example.clubmanagement.System.ListVO;

import android.graphics.drawable.Drawable;

/**
 * Created by YooJongHyeok on 2017-08-07.
 */

public class ListVO_Frg {
    private Drawable img;
    private String Title;
    private String context;
    private String CLUB_ID;


    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCLUB_ID() {
        return CLUB_ID;
    }

    public void setCLUB_ID(String CLUB_ID) {
        this.CLUB_ID = CLUB_ID;
    }
}
