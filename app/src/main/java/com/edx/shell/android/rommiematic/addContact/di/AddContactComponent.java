package com.edx.shell.android.rommiematic.addContact.di;

import com.edx.shell.android.rommiematic.RommiematicApplicationModule;
import com.edx.shell.android.rommiematic.addContact.ui.AddContactFragment;
import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Shell_Core
 */
@Singleton
@Component(modules = {AddContactModule.class, DomainModule.class, LibsModule.class, RommiematicApplicationModule.class})
public interface AddContactComponent {
    void inject(AddContactFragment fragment);
}
