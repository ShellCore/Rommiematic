package com.edx.shell.android.rommiematic.main;

import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.main.events.MainEvent;
import com.edx.shell.android.rommiematic.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Shell_Core
 */
public class MainPresenterImpl implements MainPresenter {

    // Servicios
    private MainView view;
    private EventBus eventBus;
    private SessionInteractor sessionInteractor;

    public MainPresenterImpl(MainView view, EventBus eventBus, SessionInteractor sessionInteractor) {
        this.view = view;
        this.eventBus = eventBus;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
    }

    @Override
    public String getCurrentEmail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void signOff() {
        sessionInteractor.logout();
    }
}
