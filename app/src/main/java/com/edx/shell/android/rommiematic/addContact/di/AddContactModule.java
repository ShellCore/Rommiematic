package com.edx.shell.android.rommiematic.addContact.di;

import com.edx.shell.android.rommiematic.addContact.AddContactInteractor;
import com.edx.shell.android.rommiematic.addContact.AddContactInteractorImpl;
import com.edx.shell.android.rommiematic.addContact.AddContactPresenter;
import com.edx.shell.android.rommiematic.addContact.AddContactPresenterImpl;
import com.edx.shell.android.rommiematic.addContact.AddContactRepository;
import com.edx.shell.android.rommiematic.addContact.AddContactRepositoryImpl;
import com.edx.shell.android.rommiematic.addContact.ui.AddContactView;
import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.edx.shell.android.rommiematic.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class AddContactModule {

    AddContactView view;

    public AddContactModule(AddContactView view) {
        this.view = view;
    }

    @Provides @Singleton
    AddContactView providesAddContactView() {
        return view;
    }

    @Provides @Singleton
    AddContactPresenter providesAddContactPresenter(EventBus eventBus, AddContactInteractor interactor, AddContactView view) {
        return new AddContactPresenterImpl(eventBus, interactor, view);
    }

    @Provides @Singleton
    AddContactInteractor providesAddContactInteractor(AddContactRepository repository) {
        return new AddContactInteractorImpl(repository);
    }

    @Provides @Singleton
    AddContactRepository providesAddContactRepository(EventBus eventBus, FirebaseAPI firebaseAPI) {
        return new AddContactRepositoryImpl(eventBus, firebaseAPI);
    }
}
