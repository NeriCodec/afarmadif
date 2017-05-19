package com.farmadif.neri.afarmadif;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.farmadif.neri.afarmadif.utilities.Services;

public class SignInActivity extends AppCompatActivity implements SignInListener {

    EditText usuarioEditText, contraseniaEditText;
    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usuarioEditText = (EditText) findViewById(R.id.et_usuario);
        contraseniaEditText = (EditText) findViewById(R.id.et_contrasena);
        loadingProgressBar = (ProgressBar) findViewById(R.id.pb_loading);
    }

    public void signIn() {
        Services services = new Services(this);
        services.login(usuarioEditText.getText().toString(), contraseniaEditText.getText().toString(), loadingProgressBar);




    }

    public void btnOnClick_iniciarSesion(View view) {
        loadingProgressBar.setVisibility(View.VISIBLE);
        signIn();
    }

    @Override
    public void signInValidator(boolean validator) {
        if (validator) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
