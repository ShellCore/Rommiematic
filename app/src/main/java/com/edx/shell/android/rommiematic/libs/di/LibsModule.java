package com.edx.shell.android.rommiematic.libs.di;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.edx.shell.android.rommiematic.libs.GlideImageLoader;
import com.edx.shell.android.rommiematic.libs.GreenRobotEventBus;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LibsModule {
    private Activity activity;

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager manager) {
        return new GlideImageLoader(manager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity) {
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }
    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
