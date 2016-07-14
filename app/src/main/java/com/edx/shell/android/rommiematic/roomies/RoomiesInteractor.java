package com.edx.shell.android.rommiematic.roomies;

/**
 * @author Shell_Core
 */
public interface RoomiesInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();

    void removeContact(String email);
}
