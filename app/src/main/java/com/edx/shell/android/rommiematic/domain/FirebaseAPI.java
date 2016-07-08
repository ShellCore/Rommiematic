package com.edx.shell.android.rommiematic.domain;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * @author Shell_Core
 */
public class FirebaseAPI {

    private Firebase firebase;
    private ChildEventListener listener;

    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
    }

    public void subscribe (final FirebaseEventListenerCallback listenerCallback) {
        if (listener == null) {
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listenerCallback.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listenerCallback.onChildRemoved(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    listenerCallback.onCancelled(firebaseError);
                }
            };
            firebase.addChildEventListener(listener);
        }
    }

    public void unsubscribe() {
        if (listener != null) {
            firebase.removeEventListener(listener);
        }
    }

    public String create() {
        return firebase.push().getKey();
    }

    public String getAuthEmail() {
        String email = null;
        if (firebase.getAuth() != null) {
            Map<String, Object> providerData = firebase.getAuth().getProviderData();
            email = providerData.get("email").toString();
        }
        return email;
    }

    public void logout() {
        firebase.unauth();
    }

    public void login(String email, String pass, final FirebaseActionListenerCallback listenerCallback) {
        firebase.authWithPassword(email, pass, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listenerCallback.onSuccess();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }

    public void signup(String email, String pass, final FirebaseActionListenerCallback listenerCallback) {
        firebase.createUser(email, pass, new Firebase.ValueResultHandler<Map<String, Object>>() {

            @Override
            public void onSuccess(Map<String, Object> o) {
                listenerCallback.onSuccess();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }

    public void checkForSession(FirebaseActionListenerCallback listenerCallback) {
        if (firebase.getAuth() != null) {
            listenerCallback.onSuccess();
        } else {
            listenerCallback.onError(null);
        }
    }
}
