package com.edx.shell.android.rommiematic;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class RommiematicApplicationModule {

    RommiematicApplication app;

    public RommiematicApplicationModule(RommiematicApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return app.getApplicationContext();
    }

    @Provides @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return application.getSharedPreferences(app.getSharedPreferencesName(), Context.MODE_PRIVATE);
    }

    @Provides @Singleton
    Application providesApplication() {
        return app;
    }
}
