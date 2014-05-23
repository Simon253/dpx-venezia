package com.spottechindustrial.carpool.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.spottechindustrial.carpool.android.utils.Constants;

public class LoginActivity extends Activity {
	private EditText editTextLoginEmail;
    private EditText editTextLoginPassword;
    private TextView textViewError;
    private ProgressBar progressBar;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLoginEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        editTextLoginPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        textViewError = (TextView) findViewById(R.id.textViewLoginError);
        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
	}

	public void onClickGoRegister(View v) {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivityForResult(registerIntent, Constants.INTENT_CODE_REGISTER);
    }
}
