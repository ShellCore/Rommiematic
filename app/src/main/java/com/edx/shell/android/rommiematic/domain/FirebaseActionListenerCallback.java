package com.edx.shell.android.rommiematic.domain;

import com.firebase.client.FirebaseError;

public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(FirebaseError error);
}
