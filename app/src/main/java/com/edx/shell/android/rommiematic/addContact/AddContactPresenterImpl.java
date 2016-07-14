package com.edx.shell.android.rommiematic.addContact;

import com.edx.shell.android.rommiematic.addContact.events.AddContactEvent;
import com.edx.shell.android.rommiematic.addContact.ui.AddContactView;
import com.edx.shell.android.rommiematic.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

public class AddContactPresenterImpl implements AddContactPresenter {

    private EventBus eventBus;
    private AddContactInteractor interactor;
    private AddContactView view;

    public AddContactPresenterImpl(EventBus eventBus, AddContactInteractor interactor, AddContactView view) {
        this.eventBus = eventBus;
        this.interactor = interactor;
        this.view = view;
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contactNotAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
