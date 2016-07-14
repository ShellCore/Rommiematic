package com.edx.shell.android.rommiematic.roomies;

import com.edx.shell.android.rommiematic.entities.User;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.roomies.events.RoomiesEvent;
import com.edx.shell.android.rommiematic.roomies.ui.RoomiesView;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Shell_Core
 */
public class RoomiesPresenterImpl implements RoomiesPresenter {

    // Servicios
    private EventBus eventBus;
    private RoomiesView view;
    private RoomiesInteractor interactor;

    public RoomiesPresenterImpl(EventBus eventBus, RoomiesView view, RoomiesInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        interactor.unsubscribe();
    }

    @Override
    public void onResume() {
        interactor.subscribe();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        interactor.destroyListener();
        view = null;
    }

    @Override
    @Subscribe
    public void onEventMainThread(RoomiesEvent event) {
        User user = event.getUser();
        switch (event.getType()) {
            case RoomiesEvent.ON_CONTACT_ADDED :
                onContactAdded(user);
                break;
            case RoomiesEvent.ON_CONTACT_REMOVED :
                onContactRemoved(user);
                break;
            default:
                break;
        }
    }

    @Override
    public void removeContact(String email) {
        interactor.removeContact(email);
    }

    private void onContactAdded(User user) {
        if (view != null) {
            view.onContactAdded(user);
        }
    }

    private void onContactRemoved(User user) {
        if (view != null) {
            view.onContactRemoved(user);
        }
    }
}
