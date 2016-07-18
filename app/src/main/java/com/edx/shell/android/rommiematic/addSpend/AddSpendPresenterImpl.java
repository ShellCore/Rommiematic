package com.edx.shell.android.rommiematic.addSpend;

import com.edx.shell.android.rommiematic.addSpend.events.AddSpendEvent;
import com.edx.shell.android.rommiematic.addSpend.ui.AddSpendView;
import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Shell_Core
 */
public class AddSpendPresenterImpl implements AddSpendPresenter {

    // Servicios
    private EventBus eventBus;
    private AddSpendInteractor interactor;
    private AddSpendView view;

    public AddSpendPresenterImpl(EventBus eventBus, AddSpendInteractor interactor, AddSpendView view) {
        this.eventBus = eventBus;
        this.interactor = interactor;
        this.view = view;
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
    @Subscribe
    public void onEventMainThread(AddSpendEvent event) {
        if (view != null) {
            if (event.getError() != null) {
                view.onSpendNotAdded();
            } else {
                view.onSpendAdded();
            }
        }
    }

    @Override
    public void addSpend(Spend spend) {
        interactor.execute(spend);
    }
}
