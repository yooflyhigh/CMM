package com.example.clubmanagement.System.ListVO;

import android.graphics.drawable.Drawable;

public class ListVO_Apply {
    private Drawable Apply_Img;
    private String NM;
    private String STUDENT_ID;
    private String MAJOR;
    private String GRADE;
    private String GENDER_CD;
    private String PHONE_NO;

    public Drawable getApply_Img() {
        return Apply_Img;
    }

    public void setApply_Img(Drawable apply_Img) {
        Apply_Img = apply_Img;
    }

    public String getNM() {
        return NM;
    }

    public void setNM(String NM) {
        this.NM = NM;
    }

    public String getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(String STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getMAJOR() {
        return MAJOR;
    }

    public void setMAJOR(String MAJOR) {
        this.MAJOR = MAJOR;
    }

    public String getGRADE() {
        return GRADE;
    }

    public void setGRADE(String GRADE) {
        this.GRADE = GRADE;
    }

    public String getGENDER_CD() {
        return GENDER_CD;
    }

    public void setGENDER_CD(String GENDER_CD) {
        this.GENDER_CD = GENDER_CD;
    }

    public String getPHONE_NO() {
        return PHONE_NO;
    }

    public void setPHONE_NO(String PHONE_NO) {
        this.PHONE_NO = PHONE_NO;
    }

}
