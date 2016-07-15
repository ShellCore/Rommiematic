package com.edx.shell.android.rommiematic.spends;

import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.spends.events.SpendsEvent;

/**
 * @author Shell_Core
 */
public interface SpendsPresenter {
    void onCreate();
    void onDestroy();
    void getSpends();
    void removeSpend(Spend spend);
    void onEventMainThread(SpendsEvent event);

}
