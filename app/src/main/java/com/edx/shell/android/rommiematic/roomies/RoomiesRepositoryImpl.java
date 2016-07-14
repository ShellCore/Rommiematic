package com.edx.shell.android.rommiematic.roomies;

import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.edx.shell.android.rommiematic.entities.User;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.roomies.events.RoomiesEvent;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * @author Shell_Core
 */
public class RoomiesRepositoryImpl implements RoomiesRepository {

    private ChildEventListener listener;
    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public RoomiesRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribeToRoomiesEvents() {
        if (listener == null) {
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, RoomiesEvent.ON_CONTACT_ADDED);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, RoomiesEvent.ON_CONTACT_REMOVED);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            };
        }
        firebaseAPI.getMyContactsReference()
                .addChildEventListener(listener);
    }

    @Override
    public void unsubscribeToRoomiesEvents() {

    }

    @Override
    public void destroyListener() {

    }

    @Override
    public void removeContact(String email) {

    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String email = dataSnapshot.getKey();
        email = email.replace(".", "_");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User();
        user.setEmail(email);
        post(type, user);
    }

    private void post(int type, User user) {
        RoomiesEvent event = new RoomiesEvent();
        event.setType(type);
        event.setUser(user);
        eventBus.post(event);
    }
}
