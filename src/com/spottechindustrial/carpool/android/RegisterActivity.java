package com.spottechindustrial.carpool.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	private EditText editTextRegisterEmail;
    private EditText editTextRegisterPassword;
    private TextView textViewError;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextRegisterEmail = (EditText) findViewById(R.id.editTextRegisterEmail);
        editTextRegisterPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
        textViewError = (TextView) findViewById(R.id.textViewRegisterError);
        progressBar = (ProgressBar) findViewById(R.id.progressBarRegister);
    }

    public void onClickGoLogin(View v) {
        setResult(RESULT_OK);
        finish();
    }
}
