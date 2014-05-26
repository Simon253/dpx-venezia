package com.spottechindustrial.carpool.android.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.android.volley.Response.ErrorListener;
import com.spottechindustrial.carpool.android.utils.Constants;

public class GetProfilesRequest extends CarPoolCarRequest {
    private static final String URL = Constants.SERVICES_CONSOLE_URL + Constants.GET_PROFILES_API;

    private String mUid;
    private String mAccessToken;
    private JSONArray mUidArray;

    public GetProfilesRequest(String uid, String accessToken, List<String> uidList, CarPoolCallResponseListener responseListener, ErrorListener errorListener) {
        super(Method.POST, URL, responseListener, errorListener);
        mUid = uid;
        mAccessToken = accessToken;
        mUidArray = new JSONArray(uidList);
    }

    @Override
    public Map<String, String> getParams() {
        mRequestParams = new HashMap<String, String>();
        mRequestParams.put(Constants.UID, mUid);
        mRequestParams.put(Constants.ACCESS_TOKEN, mAccessToken);
        mRequestParams.put(Constants.UID_LIST, mUidArray.toString());
        return mRequestParams;
    }
}
