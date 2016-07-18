package com.edx.shell.android.rommiematic.main.di;

import com.edx.shell.android.rommiematic.RommiematicApplicationModule;
import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;
import com.edx.shell.android.rommiematic.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Shell_Core
 */
@Singleton
@Component(modules = {MainModule.class, DomainModule.class, LibsModule.class, RommiematicApplicationModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
