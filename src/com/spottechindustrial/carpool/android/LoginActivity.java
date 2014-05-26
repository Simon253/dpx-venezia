package com.spottechindustrial.carpool.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.spottechindustrial.carpool.android.http.CarPoolCallResponseListener;
import com.spottechindustrial.carpool.android.http.GetProfilesRequest;
import com.spottechindustrial.carpool.android.http.LoginRequest;
import com.spottechindustrial.carpool.android.http.VolleyManager;
import com.spottechindustrial.carpool.android.model.Rider;
import com.spottechindustrial.carpool.android.model.Token;
import com.spottechindustrial.carpool.android.utils.Constants;
import com.spottechindustrial.carpool.android.utils.SharedPreferencesUtils;
import com.spottechindustrial.carpool.android.utils.Utils;

public class LoginActivity extends Activity implements CarPoolCallResponseListener, ErrorListener {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText editTextLoginEmail;
    private EditText editTextLoginPassword;
    private TextView textViewError;
    private ProgressBar progressBar;

    private LoginRequest mLoginRequest;
    private GetProfilesRequest mGetProfilesRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActionBar().hide();

        editTextLoginEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        editTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        textViewError = (TextView) findViewById(R.id.textViewLoginError);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);

        if (SharedPreferencesUtils.getLoginStatuFromPreferences(getApplicationContext())) {
            launchVeneziaActivity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewError.setVisibility(View.INVISIBLE);

        // if a user profile already exists, populate the email so save user some typing
        final Rider user = SharedPreferencesUtils.getUserFromPreferences(getApplicationContext());
        if (null != user && null != user.getEmail()) {
            editTextLoginEmail.setText(user.getEmail());
        }
        editTextLoginPassword.setText(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode: " + requestCode);
        Log.d(TAG, "resultCode: " + resultCode);

        final boolean isLogin = SharedPreferencesUtils.getLoginStatuFromPreferences(getApplicationContext());
        if (resultCode == RESULT_OK && requestCode == Constants.INTENT_CODE_MAIN && isLogin) {
            finish();
        }
    }

    public void onClickGoRegister(View v) {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivityForResult(registerIntent, Constants.INTENT_CODE_REGISTER);
    }

    public void onClickLogin(View v) {
        final String email = editTextLoginEmail.getText().toString();
        if (!Utils.isValidEmail(email)) {
                textViewError.setText(R.string.invalidEmail);
                textViewError.setVisibility(View.VISIBLE);
                Utils.hideSoftKeyboard(getApplicationContext(), editTextLoginEmail);
                return;
        }
        final String password = editTextLoginPassword.getText().toString();
        if (password.isEmpty()) {
            textViewError.setText(R.string.enterPassword);
            textViewError.setVisibility(View.VISIBLE);
            Utils.hideSoftKeyboard(getApplicationContext(), editTextLoginEmail);
            return;
        }
        mLoginRequest = new LoginRequest(email, password, this, this);
        progressBar.setVisibility(View.VISIBLE);
        VolleyManager.getQueueInstance(getApplicationContext()).add(mLoginRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        VolleyLog.e("Error: ", error.getMessage());
        textViewError.setText(R.string.registerError);
        textViewError.setVisibility(View.VISIBLE);
        Utils.hideSoftKeyboard(this, editTextLoginEmail);
    }

    @Override
    public void onResponse(String response, Map<String, String> responseHeaders) {
        if (responseHeaders.get(Constants.CAR_POOL_CALL_NAME).equals(Constants.LOGIN_API)
                && responseHeaders.get(Constants.CAR_POOL_CALL_ID).equals(mLoginRequest.getCarPoolCarId())) {
            Log.d(TAG, "login call response received, parsing...");
            onLoginResponse(response);
        } else if (responseHeaders.get(Constants.CAR_POOL_CALL_NAME).equals(Constants.GET_PROFILES_API)
                && responseHeaders.get(Constants.CAR_POOL_CALL_ID).equals(mGetProfilesRequest.getCarPoolCarId())) {
            Log.d(TAG, "getProfiles call response received, parsing...");
            onGetProfilesResponse(response);
        }
    }

    private void onLoginResponse(final String response) {
        try {
            final JSONObject responseJson = new JSONObject(response);
            final String status = responseJson.getString(Constants.STATUS);

            if (status.equals(CarPoolCallResponseListener.Status.SUCCESS.toString())) {
                final Token token = Utils.parseJsonToToken(responseJson.getJSONObject(Constants.DATA));
                SharedPreferencesUtils.saveTokenToPreferences(getApplicationContext(), token);

                final List<String> uidList = new ArrayList<String>();
                uidList.add(token.getUid());
                mGetProfilesRequest = new GetProfilesRequest(token.getUid(), token.getAccessToken(), uidList, this, this);
                VolleyManager.getQueueInstance(getApplicationContext()).add(mGetProfilesRequest);

            } else if (status.equals(CarPoolCallResponseListener.Status.ERROR_MISMATCH.toString())) {
                textViewError.setText(R.string.emailPasswordMismatch);
                textViewError.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            } else {
                textViewError.setText(R.string.loginError);
                textViewError.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            textViewError.setText(R.string.loginError);
            textViewError.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } finally {
            Utils.hideSoftKeyboard(this, editTextLoginEmail);
        }
    }

    private void onGetProfilesResponse(final String response) {
        try {
            final JSONObject responseJson = new JSONObject(response);
            final String status = responseJson.getString(Constants.STATUS);
            if (status.equals(CarPoolCallResponseListener.Status.SUCCESS.toString())) {
                final List<Rider> riderList = Utils.parseJsonToRiderList(responseJson.getJSONArray(Constants.DATA));
                SharedPreferencesUtils.saveUserToPreferences(getApplicationContext(), riderList.get(0));
                SharedPreferencesUtils.saveLoginStatusToPreferences(getApplicationContext(), true);
                progressBar.setVisibility(View.GONE);

                launchVeneziaActivity();
            } else {
                textViewError.setText(R.string.loginError);
                textViewError.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            textViewError.setText(R.string.loginError);
            textViewError.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } finally {
            Utils.hideSoftKeyboard(this, editTextLoginEmail);
        }
    }

    private void launchVeneziaActivity() {
        final Intent veneziaIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(veneziaIntent, Constants.INTENT_CODE_MAIN);
    }
}
