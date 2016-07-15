package com.edx.shell.android.rommiematic.spends.di;

import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.libs.base.ImageLoader;
import com.edx.shell.android.rommiematic.spends.SpendsInteractor;
import com.edx.shell.android.rommiematic.spends.SpendsInteractorImpl;
import com.edx.shell.android.rommiematic.spends.SpendsPresenter;
import com.edx.shell.android.rommiematic.spends.SpendsPresenterImpl;
import com.edx.shell.android.rommiematic.spends.SpendsRepository;
import com.edx.shell.android.rommiematic.spends.SpendsRepositoryImpl;
import com.edx.shell.android.rommiematic.spends.ui.SpendsView;
import com.edx.shell.android.rommiematic.spends.ui.adapters.OnItemClickListener;
import com.edx.shell.android.rommiematic.spends.ui.adapters.SpendsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class SpendsModule {

    private SpendsView view;
    private OnItemClickListener clickListener;

    public SpendsModule(SpendsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides @Singleton
    SpendsView providesSpendsView() {
        return view;
    }

    @Provides @Singleton
    SpendsPresenter providesSpendsPresenter(EventBus eventBus, SpendsView view, SpendsInteractor interactor) {
        return new SpendsPresenterImpl(eventBus, view, interactor);
    }

    @Provides @Singleton
    SpendsInteractor providesSpendsInteractor(SpendsRepository repository) {
        return new SpendsInteractorImpl(repository);
    }

    @Provides @Singleton
    SpendsRepository providesSpendsRepository(EventBus eventBus) {
        return new SpendsRepositoryImpl(eventBus);
    }

    @Provides @Singleton
    SpendsAdapter providesSpendsAdapter(List<Spend> spends, ImageLoader imageLoader, OnItemClickListener clickListener) {
        return new SpendsAdapter(spends, imageLoader, clickListener);
    }

    @Provides @Singleton
    List<Spend> providesSpendList() {
        return new ArrayList<>();
    }

    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return clickListener;
    }
}
