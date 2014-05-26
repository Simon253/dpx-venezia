package com.spottechindustrial.carpool.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.spottechindustrial.carpool.android.model.Rider;
import com.spottechindustrial.carpool.android.model.Token;

public class SharedPreferencesUtils {
    private static final String TAG = SharedPreferencesUtils.class.getSimpleName();

    private SharedPreferencesUtils(){}

    public static void saveLoginStatusToPreferences(final Context context, boolean isLogin) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.LOGIN_STATUS, isLogin);
        editor.commit();
        Log.d(TAG, "login status (" + String.valueOf(isLogin) + ") has been saved in the SharedPreferneces");
    }

    public static boolean getLoginStatuFromPreferences(final Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(Constants.LOGIN_STATUS, false);
    }

    public static void saveUserToPreferences(final Context context, final Rider rider) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(Constants.UID, rider.getUid());
        editor.putString(Constants.EMAIL, rider.getEmail());
        editor.putString(Constants.PASSWORD, rider.getPassword());
        editor.putString(Constants.USERNAME, rider.getUsername());
        editor.putBoolean(Constants.CAN_DRIVE, rider.getCanDrive());

        editor.putString(Constants.GENDER, String.valueOf(rider.getGender()));
        editor.putString(Constants.MAJOR, rider.getMajor());
        editor.putInt(Constants.SCHOOL_CLASS, rider.getSchoolClass());
        editor.commit();
        Log.v(TAG, "rider profile has been saved in SharedPreferneces");
    }

    public static Rider getUserFromPreferences(final Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.USER_PREFERENCE, Context.MODE_PRIVATE);
        final String uid = sharedPreferences.getString(Constants.UID, null);
        final String email = sharedPreferences.getString(Constants.EMAIL, null);
        final String password = sharedPreferences.getString(Constants.PASSWORD, null);
        final String username = sharedPreferences.getString(Constants.USERNAME, null);
        final boolean canDrive = sharedPreferences.getBoolean(Constants.CAN_DRIVE, false);

        final char gender = sharedPreferences.getString(Constants.GENDER, "?").charAt(0);
        final String major = sharedPreferences.getString(Constants.MAJOR, null);
        final int schoolClass = sharedPreferences.getInt(Constants.SCHOOL_CLASS, 0);

        final Rider rider = (new Rider.Builder())
                .setUid(uid)
                .setEmail(email)
                .setPassword(password)
                .setUsername(username)
                .setCanDrive(canDrive)
                .build();
        rider.setGender(gender);
        rider.setMajor(major);
        rider.setSchoolClass(schoolClass);
        Log.d(TAG, "rider profile has been retrieved from SharedPreferneces");
        return rider;
    }

    public static void saveTokenToPreferences(final Context context, final Token token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.TOKEN_PREFERENCE, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        editor.putString(Constants.UID, token.getUid());
        editor.putString(Constants.ACCESS_TOKEN, token.getAccessToken());
        editor.putString(Constants.REFRESH_TOKEN, token.getRefreshToken());
        editor.putLong(Constants.TIMESTAMP, token.getTimestamp());
        editor.commit();
        Log.d(TAG, "rider token has been saved in SharedPreferneces");
    }

    public static Token getTokenFromPreferences(final Context context) {
        final SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.TOKEN_PREFERENCE, Context.MODE_PRIVATE);
        final String uid = sharedPreferences.getString(Constants.UID, null);
        final String accessToken = sharedPreferences.getString(Constants.ACCESS_TOKEN, null);
        final String refreshToken = sharedPreferences.getString(Constants.REFRESH_TOKEN, null);
        final long timestamp = sharedPreferences.getLong(Constants.TIMESTAMP, 0);

        final Token token = (new Token.Builder())
                .setUid(uid)
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setTimestamp(timestamp)
                .build();
        Log.d(TAG, "rider token has been retrieved from SharedPreferneces");
        return token;
    }
}
