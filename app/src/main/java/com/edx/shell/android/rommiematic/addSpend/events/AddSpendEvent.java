package com.edx.shell.android.rommiematic.addSpend.events;

/**
 * @author Shell_Core
 */
public class AddSpendEvent {

    public static final int ON_SPEND_ADDED = 0;

    private int type;
    private String error;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
