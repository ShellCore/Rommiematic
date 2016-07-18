package com.edx.shell.android.rommiematic.roomies;

import com.edx.shell.android.rommiematic.roomies.events.RoomiesEvent;

/**
 * @author Shell_Core
 */
public interface RoomiesPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
    void onEventMainThread(RoomiesEvent event);
    void removeContact(String email);
}
