package com.example.clubmanagement.Login.Auto_Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_PASS = "password";
    static final String AUTO_LOGIN_CHECK = "check";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    // 계정 정보 저장
    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setUserPass(Context ctx, String userPass) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PASS, userPass);
        editor.commit();
    }

    public static void setAutoLoginCheck(Context ctx, boolean check) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(AUTO_LOGIN_CHECK, check);
        editor.commit();
    }

    // 저장된 정보 가져오기
    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserPass(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_PASS, "");
    }

    public static boolean getIsEnabled(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(AUTO_LOGIN_CHECK, false);
    }
    // 로그아웃
    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
