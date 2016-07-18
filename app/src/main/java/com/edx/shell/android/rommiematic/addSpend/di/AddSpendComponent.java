package com.edx.shell.android.rommiematic.addSpend.di;

import com.edx.shell.android.rommiematic.RommiematicApplicationModule;
import com.edx.shell.android.rommiematic.addSpend.ui.AddSpendActivity;
import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Shell_Core
 */
@Singleton
@Component(modules = {AddSpendModule.class, DomainModule.class, LibsModule.class, RommiematicApplicationModule.class})
public interface AddSpendComponent {
    void inject(AddSpendActivity activity);
}
