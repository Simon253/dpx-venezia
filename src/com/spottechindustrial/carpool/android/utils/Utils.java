package com.spottechindustrial.carpool.android.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.spottechindustrial.carpool.android.model.Rider;
import com.spottechindustrial.carpool.android.model.Token;

public class Utils {
    private Utils() {}

    public static boolean isValidEmail(final String email) {
        final Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        final Matcher m = p.matcher(email);
        return m.matches();
    }

    public static void hideSoftKeyboard(final Context context, final EditText editText){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static Token parseJsonToToken(final JSONObject json) throws JSONException {
        final String uid = json.getString(Constants.UID);
        final String accessToken = json.getString(Constants.ACCESS_TOKEN);
        final String refreshToken = json.getString(Constants.REFRESH_TOKEN);
        final long timestamp = json.getLong(Constants.TIMESTAMP);
        final Token token = (new Token.Builder())
                .setUid(uid)
                .setAccessToken(accessToken)
                .setRefreshToken(refreshToken)
                .setTimestamp(timestamp)
                .build();
        return token;
    }

    public static List<Rider> parseJsonToRiderList(final JSONArray jsonArray) throws JSONException {
        final List<Rider> riderList = new ArrayList<Rider>();
        for (int i = 0; i < jsonArray.length(); i++) {
            final JSONObject jsonObject = jsonArray.getJSONObject(i);
            riderList.add(parseJsonToRider(jsonObject));
        }
        return riderList;
    }

    private static Rider parseJsonToRider(final JSONObject jsonObject) throws JSONException {
        final String uid = jsonObject.getString(Constants.UID);
        final String email = jsonObject.getString(Constants.EMAIL);
        final String password = jsonObject.getString(Constants.PASSWORD);
        final String username = jsonObject.getString(Constants.USERNAME);
        final boolean canDrive = jsonObject.getBoolean(Constants.CAN_DRIVE);

        final Rider rider = (new Rider.Builder())
                .setUid(uid)
                .setEmail(email)
                .setPassword(password)
                .setUsername(username)
                .setCanDrive(canDrive)
                .build();
        rider.setGender(jsonObject.getString(Constants.GENDER).charAt(0));
        rider.setMajor(jsonObject.getString(Constants.MAJOR));
        rider.setSchoolClass(jsonObject.getInt(Constants.SCHOOL_CLASS));
        return rider;
    }
}
