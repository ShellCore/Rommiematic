package com.edx.shell.android.rommiematic.login;

import com.edx.shell.android.rommiematic.login.events.LoginEvent;

/**
 * @author Shell_Core
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();

    void validateLogin(String email, String pass);
    void registerNewUser(String email, String pass);

    void onEventMainThread(LoginEvent event);
}
