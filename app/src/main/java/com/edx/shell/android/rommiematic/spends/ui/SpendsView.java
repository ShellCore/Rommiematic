package com.edx.shell.android.rommiematic.spends.ui;

import com.edx.shell.android.rommiematic.entities.Spend;

/**
 * @author Shell_Core
 */
public interface SpendsView {
    void onSpendAdded(Spend spend);
    void onSpendRemoved(Spend spend);
}
