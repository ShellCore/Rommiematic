package com.edx.shell.android.rommiematic.login.di;

import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.login.LoginInteractor;
import com.edx.shell.android.rommiematic.login.LoginInteractorImpl;
import com.edx.shell.android.rommiematic.login.LoginPresenter;
import com.edx.shell.android.rommiematic.login.LoginPresenterImpl;
import com.edx.shell.android.rommiematic.login.LoginRepository;
import com.edx.shell.android.rommiematic.login.LoginRepositoryImpl;
import com.edx.shell.android.rommiematic.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class LoginModule {

    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    @Singleton
    LoginView providesLoginView() {
        return loginView;
    }

    @Provides @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView view, LoginInteractor interactor) {
        return new LoginPresenterImpl(eventBus, view, interactor);
    }

    @Provides @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides @Singleton
    LoginRepository providesLoginRepository(EventBus eventBus, FirebaseAPI firebaseAPI) {
        return new LoginRepositoryImpl(eventBus, firebaseAPI);
    }
}
