package com.edx.shell.android.rommiematic.main.di;

import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.main.MainPresenter;
import com.edx.shell.android.rommiematic.main.MainPresenterImpl;
import com.edx.shell.android.rommiematic.main.MainRepository;
import com.edx.shell.android.rommiematic.main.MainRepositoryImpl;
import com.edx.shell.android.rommiematic.main.SessionInteractor;
import com.edx.shell.android.rommiematic.main.SessionInteractorImpl;
import com.edx.shell.android.rommiematic.main.ui.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    MainView providesMainView() {
        return view;
    }

    @Provides @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, SessionInteractor sessionInteractor) {
        return new MainPresenterImpl(view, eventBus, sessionInteractor);
    }

    @Provides @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository) {
        return new SessionInteractorImpl(repository);
    }

    @Provides @Singleton
    MainRepository providesMainRepository(FirebaseAPI firebaseAPI) {
        return new MainRepositoryImpl(firebaseAPI);
    }
}
