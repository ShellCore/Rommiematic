package com.edx.shell.android.rommiematic;

import android.app.Application;

import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;
import com.edx.shell.android.rommiematic.login.di.DaggerLoginComponent;
import com.edx.shell.android.rommiematic.login.di.LoginComponent;
import com.edx.shell.android.rommiematic.login.di.LoginModule;
import com.edx.shell.android.rommiematic.login.ui.LoginView;
import com.firebase.client.Firebase;

/**
 * @author Shell_Core
 */
public class RommiematicApplication extends Application {

    // Constantes
    public static final String EMAIL_KEY = "email";
    public static final String SHARED_PREFERENCES_NAME = "UserPrefs";
    public static final String FIREBASE_URL = "https://rommiematic.firebaseio.com/";

    private RommiematicApplicationModule appModule;
    private DomainModule domainModule;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        initModules();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
    }

    private void initModules() {
        appModule = new RommiematicApplicationModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    public static String getEmailKey() {
        return EMAIL_KEY;
    }

    public static String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent.builder()
                .rommiematicApplicationModule(appModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();
    }
}
