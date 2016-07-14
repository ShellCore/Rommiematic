package com.edx.shell.android.rommiematic.spends;

import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.spends.events.SpendsEvent;

/**
 * @author Shell_Core
 */
public interface SpendsPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(SpendsEvent event);
    void removeSpend(Spend spend);
}
