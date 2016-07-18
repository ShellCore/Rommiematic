package com.edx.shell.android.rommiematic.main;

import com.edx.shell.android.rommiematic.main.events.MainEvent;

/**
 * @author Shell_Core
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(MainEvent event);

    String getCurrentEmail();
    void signOff();
}
