package com.edx.shell.android.rommiematic.spends.events;

import com.edx.shell.android.rommiematic.entities.Spend;

import java.util.List;

/**
 * @author Shell_Core
 */
public class SpendsEvent {

    public static final int ON_SPEND_READED = 0;
    public static final int ON_SPEND_ADDDED = 1;
    public static final int ON_SPEND_REMOVED = 2;

    private int type;
    private List<Spend> spends;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Spend> getSpends() {
        return spends;
    }

    public void setSpends(List<Spend> spends) {
        this.spends = spends;
    }
}
