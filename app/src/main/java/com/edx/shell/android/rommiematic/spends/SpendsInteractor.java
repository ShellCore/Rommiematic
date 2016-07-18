package com.edx.shell.android.rommiematic.spends;

import com.edx.shell.android.rommiematic.entities.Spend;

/**
 * @author Shell_Core
 */
public interface SpendsInteractor {
    void getSpends();
    void removeSpend(Spend spend);
}
