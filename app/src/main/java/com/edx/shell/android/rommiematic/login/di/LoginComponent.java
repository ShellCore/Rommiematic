package com.edx.shell.android.rommiematic.login.di;

import com.edx.shell.android.rommiematic.RommiematicApplicationModule;
import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;
import com.edx.shell.android.rommiematic.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Shell_Core
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, RommiematicApplicationModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
