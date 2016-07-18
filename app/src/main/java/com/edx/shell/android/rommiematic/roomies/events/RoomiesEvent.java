package com.edx.shell.android.rommiematic.roomies.events;

import com.edx.shell.android.rommiematic.entities.User;

/**
 * @author Shell_Core
 */
public class RoomiesEvent {

    public static final int ON_CONTACT_ADDED = 0;
    public static final int ON_CONTACT_REMOVED = 1;

    private int type;
    private String error;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
