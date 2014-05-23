package com.spottechindustrial.carpool.android.http;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.spottechindustrial.carpool.android.utils.Constants;

public class RegisterRequest extends Request<String> {
    private static final String URL = Constants.SERVICES_CONSOLE_URL + Constants.REGISTER_API;

    private String mEmail;
    private String mPassword;
    private Map<String, String> mParams;

    public RegisterRequest(final String email, final String password, final ErrorListener listener) {
        super(Method.POST, URL, listener);

        mEmail = email;
        mPassword = password;
    }

    @Override
    public Map<String, String> getParams() {
    	mParams = new HashMap<String, String>();
        mParams.put(Constants.EMAIL, mEmail);
        mParams.put(Constants.PASSWORD, mPassword);
        return mParams;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void deliverResponse(String response) {
        // TODO Auto-generated method stub
        
    }
}
