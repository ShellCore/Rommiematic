package com.edx.shell.android.rommiematic.roomies.ui;

import com.edx.shell.android.rommiematic.entities.User;

/**
 * @author Shell_Core
 */
public interface RoomiesView {
    public void onContactAdded(User user);
    public void onContactRemoved(User user);
}
