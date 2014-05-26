package com.spottechindustrial.carpool.android;

import java.util.Map;

import android.app.Activity;
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
import com.spottechindustrial.carpool.android.http.RegisterRequest;
import com.spottechindustrial.carpool.android.http.VolleyManager;
import com.spottechindustrial.carpool.android.utils.Utils;

public class RegisterActivity extends Activity implements CarPoolCallResponseListener, ErrorListener{
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private EditText editTextRegisterEmail;
    private EditText editTextRegisterPassword;
    private TextView textViewError;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getActionBar().hide();

        editTextRegisterEmail = (EditText) findViewById(R.id.editTextRegisterEmail);
        editTextRegisterPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
        textViewError = (TextView) findViewById(R.id.textViewRegisterError);
        progressBar = (ProgressBar) findViewById(R.id.progressBarRegister);
    }

    public void onClickRegister(View v) {
        final String email = editTextRegisterEmail.getText().toString();
        if (!Utils.isValidEmail(email)) {
            textViewError.setText(R.string.invalidEmail);
            textViewError.setVisibility(View.VISIBLE);
            Utils.hideSoftKeyboard(this, editTextRegisterEmail);
            return;
        }
        final String password = editTextRegisterPassword.getText().toString();
        if (password.isEmpty()) {
            textViewError.setText(R.string.enterPassword);
            textViewError.setVisibility(View.VISIBLE);
            Utils.hideSoftKeyboard(this, editTextRegisterEmail);
            return;
        }
        final RegisterRequest registerRequest = new RegisterRequest(email, password, this, this);
        progressBar.setVisibility(View.VISIBLE);
        VolleyManager.getQueueInstance(getApplicationContext()).add(registerRequest);
    }

    public void onClickGoLogin(View v) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        VolleyLog.e("Error: ", error.getMessage());
        textViewError.setText(R.string.registerError);
        textViewError.setVisibility(View.VISIBLE);
        Utils.hideSoftKeyboard(RegisterActivity.this, editTextRegisterEmail);
    }

    @Override
    public void onResponse(final String response, final Map<String, String> responseHeaders) {
        progressBar.setVisibility(View.GONE);
        Log.d(TAG, response);
    }
}
