package com.edx.shell.android.rommiematic.addSpend;

import com.edx.shell.android.rommiematic.addSpend.events.AddSpendEvent;
import com.edx.shell.android.rommiematic.entities.Spend;

/**
 * @author Shell_Core
 */
public interface AddSpendPresenter {
    void onCreate();
    void onDestroy();
    void onEventMainThread(AddSpendEvent event);

    void addSpend(Spend spend);
}
