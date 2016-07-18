package com.edx.shell.android.rommiematic.spends;

import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.spends.events.SpendsEvent;
import com.edx.shell.android.rommiematic.spends.ui.SpendsView;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Shell_Core
 */
public class SpendsPresenterImpl implements SpendsPresenter {

    // Servicios
    private EventBus eventBus;
    private SpendsView view;
    private SpendsInteractor interactor;

    public SpendsPresenterImpl(EventBus eventBus, SpendsView view, SpendsInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void getSpends() {
        interactor.getSpends();
    }

    @Override
    @Subscribe
    public void onEventMainThread(SpendsEvent event) {
        switch (event.getType()) {
            case SpendsEvent.ON_SPEND_READED:
                view.setSpends(event.getSpends());
                break;
            case SpendsEvent.ON_SPEND_REMOVED:
                view.onSpendRemoved(event.getSpends().get(0));
                break;
            default:
                break;
        }
    }

    @Override
    public void removeSpend(Spend spend) {
        interactor.removeSpend(spend);
    }
}
