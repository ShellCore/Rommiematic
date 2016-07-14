package com.edx.shell.android.rommiematic.roomies;

/**
 * @author Shell_Core
 */
public interface RoomiesRepository {
    void subscribeToRoomiesEvents();
    void unsubscribeToRoomiesEvents();
    void destroyListener();

    void removeContact(String email);
}
