package com.edx.shell.android.rommiematic.entities;

import java.util.List;

/**
 * @author Shell_Core
 */
public class User {

    private String email;
    private List<String> roomies;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoomies() {
        return roomies;
    }

    public void setRoomies(List<String> roomies) {
        this.roomies = roomies;
    }
}
