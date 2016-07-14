package com.edx.shell.android.rommiematic.roomies.di;

import com.edx.shell.android.rommiematic.domain.FirebaseAPI;
import com.edx.shell.android.rommiematic.entities.User;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.libs.base.ImageLoader;
import com.edx.shell.android.rommiematic.roomies.RoomiesInteractor;
import com.edx.shell.android.rommiematic.roomies.RoomiesInteractorImpl;
import com.edx.shell.android.rommiematic.roomies.RoomiesPresenter;
import com.edx.shell.android.rommiematic.roomies.RoomiesPresenterImpl;
import com.edx.shell.android.rommiematic.roomies.RoomiesRepository;
import com.edx.shell.android.rommiematic.roomies.RoomiesRepositoryImpl;
import com.edx.shell.android.rommiematic.roomies.ui.RoomiesView;
import com.edx.shell.android.rommiematic.roomies.ui.adapters.OnItemClickListener;
import com.edx.shell.android.rommiematic.roomies.ui.adapters.RoomiesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Shell_Core
 */
@Module
public class RoomiesModule {

    private RoomiesView view;
    private OnItemClickListener clickListener;

    public RoomiesModule(RoomiesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    RoomiesView providesRoomiesView() {
        return view;
    }

    @Provides @Singleton
    RoomiesPresenter providesRoomiesPresenter(EventBus eventBus, RoomiesView view, RoomiesInteractor interactor) {
        return new RoomiesPresenterImpl(eventBus, view, interactor);
    }

    @Provides @Singleton
    RoomiesInteractor providesRoomiesInteractor(RoomiesRepository repository) {
        return new RoomiesInteractorImpl(repository);
    }

    @Provides @Singleton
    RoomiesRepository providesRoomiesRepository(EventBus eventBus, FirebaseAPI firebaseAPI) {
        return new RoomiesRepositoryImpl(eventBus, firebaseAPI);
    }

    @Provides @Singleton
    RoomiesAdapter providesRoomiesAdapter(List<User> roomies, ImageLoader imageLoader, OnItemClickListener clickListener) {
        return new RoomiesAdapter(roomies, imageLoader, clickListener);
    }
    
    @Provides @Singleton
    List<User> providesUserList() {
        return new ArrayList<>();
    }
    
    @Provides @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return clickListener;
    }
}
