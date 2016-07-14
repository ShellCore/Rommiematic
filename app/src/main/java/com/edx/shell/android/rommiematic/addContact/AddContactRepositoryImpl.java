package com.edx.shell.android.rommiematic.addContact;

import com.edx.shell.android.rommiematic.addContact.events.AddContactEvent;
import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.edx.shell.android.rommiematic.entities.User;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AddContactRepositoryImpl implements AddContactRepository {

    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public AddContactRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void addContact(final String email) {
        final String key = email.replace(".", "_");
        Firebase userReference = firebaseAPI.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    Firebase myContactReference = firebaseAPI.getMyContactsReference();
                    myContactReference.child(key).setValue(true);
                    String currentUserKey = firebaseAPI.getAuthEmail();
                    currentUserKey = currentUserKey.replace(".", "_");
                    Firebase reverseContactReference = firebaseAPI.getContactsReference(email);
                    reverseContactReference.child(currentUserKey)
                            .setValue(true);
                    postSuccess();
                } else {
                    postError();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                postError();
            }
        });
    }

    private void postSuccess() {
        post(false);
    }

    private void postError() {
        post(true);
    }

    private void post(boolean error) {
        AddContactEvent event = new AddContactEvent();
        event.setError(error);
        eventBus.post(event);
    }
}
