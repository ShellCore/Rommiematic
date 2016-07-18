package com.edx.shell.android.rommiematic.addSpend.di;

import com.edx.shell.android.rommiematic.addSpend.AddSpendInteractor;
import com.edx.shell.android.rommiematic.addSpend.AddSpendInteractorImpl;
import com.edx.shell.android.rommiematic.addSpend.AddSpendPresenter;
import com.edx.shell.android.rommiematic.addSpend.AddSpendPresenterImpl;
import com.edx.shell.android.rommiematic.addSpend.AddSpendRepository;
import com.edx.shell.android.rommiematic.addSpend.AddSpendRepositoryImpl;
import com.edx.shell.android.rommiematic.addSpend.ui.AddSpendView;
import com.edx.shell.android.rommiematic.libs.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class AddSpendModule {
    
    AddSpendView view;

    public AddSpendModule(AddSpendView view) {
        this.view = view;
    }
    
    @Provides
    @Singleton
    AddSpendView providesAddSpendView() {
        return view;
    }
    
    @Provides @Singleton
    AddSpendPresenter providesAddSpendPresenter(EventBus eventBus, AddSpendInteractor interactor, AddSpendView view) {
        return new AddSpendPresenterImpl(eventBus, interactor, view);
    }

    @Provides @Singleton
    AddSpendInteractor providesAddSpendInteractor(AddSpendRepository repository) {
        return new AddSpendInteractorImpl(repository);
    }

    @Provides @Singleton
    AddSpendRepository providesAddSpendRepository(EventBus eventBus) {
        return new AddSpendRepositoryImpl(eventBus);
    }
}
