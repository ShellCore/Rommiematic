package com.edx.shell.android.rommiematic.roomies.ui;

import com.edx.shell.android.rommiematic.entities.User;

/**
 * @author Shell_Core
 */
public interface RoomiesView {
    void onContactAdded(User user);
    void onContactRemoved(User user);
}
