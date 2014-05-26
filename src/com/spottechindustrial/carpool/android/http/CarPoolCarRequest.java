package com.spottechindustrial.carpool.android.http;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.spottechindustrial.carpool.android.utils.Constants;

public abstract class CarPoolCarRequest extends Request<String>{
    private static final String TAG = CarPoolCarRequest.class.getSimpleName();

    protected Map<String, String> mRequestParams;
    protected Map<String, String> mResponseHeaders;
    private CarPoolCallResponseListener mResponseListener;
    private String mCarPoolCallId;

    public CarPoolCarRequest(int method, String url, CarPoolCallResponseListener responseListener, ErrorListener errorListener) {
        super(method, url, errorListener);
        mResponseListener = responseListener;
        mCarPoolCallId = UUID.randomUUID().toString();
        mResponseHeaders = null;
    }

    public String getCarPoolCarId() {
        return mCarPoolCallId;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> requestHeaders = super.getHeaders();

        if (requestHeaders == null || requestHeaders.equals(Collections.emptyMap())) {
            requestHeaders = new HashMap<String, String>();
        }
        requestHeaders.put(Constants.CAR_POOL_CALL_ID, mCarPoolCallId);
        return requestHeaders;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            mResponseHeaders = response.headers;
            final String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mResponseListener.onResponse(response, mResponseHeaders);
    }
}
