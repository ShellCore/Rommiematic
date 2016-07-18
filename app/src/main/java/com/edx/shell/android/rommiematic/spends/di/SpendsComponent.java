package com.edx.shell.android.rommiematic.spends.di;

import com.edx.shell.android.rommiematic.RommiematicApplicationModule;
import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;
import com.edx.shell.android.rommiematic.spends.ui.SpendsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Shell_Core
 */
@Singleton
@Component(modules = {SpendsModule.class, DomainModule.class, LibsModule.class, RommiematicApplicationModule.class})
public interface SpendsComponent {
    void inject(SpendsActivity activity);
}
