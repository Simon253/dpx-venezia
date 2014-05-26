package com.spottechindustrial.carpool.android.http;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.spottechindustrial.carpool.android.utils.Constants;

public class LoginRequest extends CarPoolCarRequest {
    private static final String URL = Constants.SERVICES_CONSOLE_URL + Constants.LOGIN_API;

    private String mEmail;
    private String mPassword;

    public LoginRequest(final String email, final String password, final CarPoolCallResponseListener responseListener, final ErrorListener errorListener) {
        super(Method.POST, URL, responseListener, errorListener);
        mEmail = email;
        mPassword = password;
    }

    @Override
    public Map<String, String> getParams() {
        mRequestParams = new HashMap<String, String>();
        mRequestParams.put(Constants.EMAIL, mEmail);
        mRequestParams.put(Constants.PASSWORD, mPassword);
        return mRequestParams;
    }
}
