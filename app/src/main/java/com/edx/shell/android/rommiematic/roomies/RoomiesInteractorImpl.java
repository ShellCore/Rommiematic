package com.edx.shell.android.rommiematic.roomies;

/**
 * @author Shell_Core
 */
public class RoomiesInteractorImpl implements RoomiesInteractor {

    private RoomiesRepository repository;

    public RoomiesInteractorImpl(RoomiesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribeToRoomiesEvents();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribeToRoomiesEvents();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
