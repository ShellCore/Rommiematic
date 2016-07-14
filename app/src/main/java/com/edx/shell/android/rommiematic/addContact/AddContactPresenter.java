package com.edx.shell.android.rommiematic.addContact;

import com.edx.shell.android.rommiematic.addContact.events.AddContactEvent;

public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
