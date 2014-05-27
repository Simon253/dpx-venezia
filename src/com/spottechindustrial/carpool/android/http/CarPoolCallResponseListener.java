package com.spottechindustrial.carpool.android.http;

import java.util.Map;

public interface CarPoolCallResponseListener {
    public enum Status {
        SUCCESS,
        FAILED,
        ERROR_IN_USE,
        ERROR_MISMATCH,
        ERROR_EXPIRED,
        ERROR_PARAMS
    }

    public void onResponse(final String response, final Map<String, String> responseHeaders);
}
