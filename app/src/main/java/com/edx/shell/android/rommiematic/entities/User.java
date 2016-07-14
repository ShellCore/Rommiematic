package com.edx.shell.android.rommiematic.entities;

import java.util.Map;

/**
 * @author Shell_Core
 */
public class User {

    private String email;
    Map<String, Boolean> contacts;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        boolean equal = false;

        if (o instanceof User) {
            User user = (User) o;
            equal = email.equals(user.getEmail());
        }

        return equal;
    }
}
