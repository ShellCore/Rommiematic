package com.edx.shell.android.rommiematic.spends.events;

import com.edx.shell.android.rommiematic.entities.Spend;

/**
 * @author Shell_Core
 */
public class SpendsEvent {

    public static final int ON_SPEND_ADDDED = 0;
    public static final int ON_SPEND_REMOVED = 1;

    private int type;
    private String error;
    private Spend spend;

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

    public Spend getSpend() {
        return spend;
    }

    public void setSpend(Spend spend) {
        this.spend = spend;
    }
}
