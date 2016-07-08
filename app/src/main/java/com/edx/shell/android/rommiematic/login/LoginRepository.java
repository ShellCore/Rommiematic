package com.edx.shell.android.rommiematic.login;

/**
 * @author Shell_Core
 */
public interface LoginRepository {
    void signin(String user, String pass);
    void signup(String user, String pass);
}
