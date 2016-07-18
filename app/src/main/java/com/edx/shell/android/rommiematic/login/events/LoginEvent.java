package com.edx.shell.android.rommiematic.login.events;

/**
 * @author Shell_Core
 */
public class LoginEvent {

    // Constantes de estados de evento de login o creación de cuenta
    public static final int ON_SIGNIN_ERROR = 0;
    public static final int ON_SIGNUP_ERROR = 1;
    public static final int ON_FAILED_TO_RECOVER_SESSION = 2;
    public static final int ON_SIGNIN_SUCCESS = 3;
    public static final int ON_SIGNUP_SUCCESS = 4;

    // Variables necesarias para el control de los eventos
    private int    eventType;
    private String error;
    private String currentEmail;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }
}
