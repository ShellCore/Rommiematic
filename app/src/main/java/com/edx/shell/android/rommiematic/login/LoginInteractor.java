package com.edx.shell.android.rommiematic.login;

/**
 * @author Shell_Core
 */
public interface LoginInteractor {
    void doSignin(String user, String pass);
    void doSignup(String user, String pass);
}
