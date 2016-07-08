package com.edx.shell.android.rommiematic.domain.di;

import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class DomainModule {

    String firebaseUrl;

    public DomainModule(String firebaseUrl) {
        this.firebaseUrl = firebaseUrl;
    }

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(Firebase firebase) {
        return new FirebaseAPI(firebase);
    }

    @Provides @Singleton
    Firebase providesFirebase(String firebaseUrl) {
        return new Firebase(firebaseUrl);
    }

    @Provides @Singleton
    String providesFirebaseUrl() {
        return firebaseUrl;
    }
}
