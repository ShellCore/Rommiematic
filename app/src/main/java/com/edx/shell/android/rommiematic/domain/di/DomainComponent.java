package com.edx.shell.android.rommiematic.domain.di;

import com.edx.shell.android.rommiematic.RommiematicApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Shell_Core
 */
@Singleton
@Component(modules = {DomainModule.class, RommiematicApplicationModule.class})
public interface DomainComponent {
}
