package com.edx.shell.android.rommiematic.addSpend;

import com.edx.shell.android.rommiematic.addSpend.events.AddSpendEvent;
import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.libs.base.EventBus;

/**
 * @author Shell_Core
 */
public class AddSpendRepositoryImpl implements AddSpendRepository {

    // Servicios
    private EventBus eventBus;

    public AddSpendRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void addSpend(Spend spend) {
        spend.save();
        post(AddSpendEvent.ON_SPEND_ADDED, spend);
    }

    private void post(int type, Spend spend) {
        AddSpendEvent event = new AddSpendEvent();
        event.setType(type);
        eventBus.post(event);
    }
}
