package com.edx.shell.android.rommiematic.spends.ui;

import com.edx.shell.android.rommiematic.entities.Spend;

import java.util.List;

/**
 * @author Shell_Core
 */
public interface SpendsView {
    void onSpendAdded(Spend spend);
    void onSpendRemoved(Spend spend);
    void addSpend();
    void setSpends(List<Spend> data);
}
