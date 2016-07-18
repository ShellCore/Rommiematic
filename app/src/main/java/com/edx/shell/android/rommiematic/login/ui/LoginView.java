package com.edx.shell.android.rommiematic.login.ui;

/**
 * @author Shell_Core
 */
public interface LoginView {
    void showInputs();
    void hideInputs();
    void showProgressbar();
    void hideProgressbar();

    void handleSignin();
    void handleSingup();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

    void setUserEmail(String email);
}
