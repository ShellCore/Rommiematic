package com.edx.shell.android.rommiematic.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.edx.shell.android.rommiematic.MainActivity;
import com.edx.shell.android.rommiematic.R;
import com.edx.shell.android.rommiematic.RommiematicApplication;
import com.edx.shell.android.rommiematic.login.LoginPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Shell_Core
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.container)
    CoordinatorLayout container;
    // Variables
    private RommiematicApplication app;

    // Servicios
    @Inject
    LoginPresenter presenter;
    @Inject
    SharedPreferences sharedPreferences;


    // Componentes de la vlista
    @Bind(R.id.edt_user)
    EditText edtUser;
    @Bind(R.id.til_user)
    TextInputLayout tilUser;
    @Bind(R.id.edt_password)
    EditText edtPassword;
    @Bind(R.id.til_password)
    TextInputLayout tilPassword;
    @Bind(R.id.prg_login)
    ProgressBar prgLogin;
    @Bind(R.id.lnr_buttons)
    LinearLayout lnrButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        app = (RommiematicApplication) getApplication();
        setupInjection();
        presenter.onCreate();
        presenter.validateLogin(null, null);
    }

    private void setupInjection() {
        app.getLoginComponent(this)
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showInputs() {
        setInputs(View.VISIBLE);
    }

    @Override
    public void hideInputs() {
        setInputs(View.GONE);
    }

    @Override
    public void showProgressbar() {
        prgLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        prgLogin.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_signin)
    @Override
    public void handleSignin() {
        tilUser.setError(null);
        presenter.validateLogin(edtUser.getText().toString(),
                edtPassword.getText().toString());
    }

    @OnClick(R.id.btn_signup)
    @Override
    public void handleSingup() {
        presenter.registerNewUser(edtUser.getText().toString(),
                edtPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginError(String error) {
        edtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        tilUser.setError(msgError);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void newUserError(String error) {
        edtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        tilUser.setError(msgError);
    }

    @Override
    public void setUserEmail(String email) {
        if (email != null) {
            sharedPreferences.edit()
                    .putString(app.getEmailKey(), email)
                    .commit();
        }
    }

    private void setInputs(int status) {
        tilUser.setVisibility(status);
        tilPassword.setVisibility(status);
        lnrButtons.setVisibility(status);
    }
}
