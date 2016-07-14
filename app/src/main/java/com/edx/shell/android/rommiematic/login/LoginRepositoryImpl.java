package com.edx.shell.android.rommiematic.login;

import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.edx.shell.android.rommiematic.domain.FirebaseActionListenerCallback;
import com.edx.shell.android.rommiematic.entities.User;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.login.events.LoginEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * @author Shell_Core
 */
public class LoginRepositoryImpl implements LoginRepository {

    // Servicios
    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public LoginRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void signin(String user, String pass) {
        if (user != null && pass != null) {
            firebaseAPI.login(user, pass, new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebaseAPI.getAuthEmail();
                    initSignin();
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.ON_SIGNIN_ERROR, error.getMessage(), null);
                }
            });
        } else {
            firebaseAPI.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebaseAPI.getAuthEmail();
                    postEvent(LoginEvent.ON_SIGNIN_SUCCESS, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.ON_FAILED_TO_RECOVER_SESSION);
                }
            });
        }
    }

    private void initSignin() {
        firebaseAPI.getMyUserReference()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User currentUser = dataSnapshot.getValue(User.class);
                        if (currentUser == null) {
                            registerNewUser();
                        }
                        postEvent(LoginEvent.ON_SIGNIN_SUCCESS);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
    }

    private void registerNewUser() {
        String email = firebaseAPI.getAuthEmail();
        if (email != null) {
            User currentUser = new User();
            currentUser.setEmail(email);
            firebaseAPI.getMyUserReference().setValue(currentUser);
        }
    }

    @Override
    public void signup(final String user, final String pass) {
        firebaseAPI.signup(user, pass, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                postEvent(LoginEvent.ON_SIGNUP_SUCCESS);
                signin(user, pass);
            }

            @Override
            public void onError(FirebaseError error) {
                postEvent(LoginEvent.ON_SIGNUP_ERROR, error.getMessage(), null);
            }
        });
    }

    private void postEvent(int type, String errorMessage, String currentUserEmail) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setError(errorMessage);
        loginEvent.setCurrentEmail(currentUserEmail);
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null, null);
    }

    private void postEvent(int type, String currentUserEmail) {
        postEvent(type, null, currentUserEmail);
    }
}
