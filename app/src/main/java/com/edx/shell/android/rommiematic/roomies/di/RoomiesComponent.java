package com.edx.shell.android.rommiematic.roomies.di;

import com.edx.shell.android.rommiematic.RommiematicApplicationModule;
import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;
import com.edx.shell.android.rommiematic.roomies.ui.RoomiesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Shell_Core
 */
@Singleton
@Component(modules = {RoomiesModule.class, DomainModule.class, LibsModule.class, RommiematicApplicationModule.class})
public interface RoomiesComponent {
    void inject(RoomiesActivity activity);
}
