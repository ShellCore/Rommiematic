package com.edx.shell.android.rommiematic.spends;

import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.libs.base.EventBus;
import com.edx.shell.android.rommiematic.spends.events.SpendsEvent;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Arrays;
import java.util.List;

/**
 * @author Shell_Core
 */
public class SpendsRepositoryImpl implements SpendsRepository {

    // Servicios
    private EventBus eventBus;

    public SpendsRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedSpends() {
        List<Spend> spends = new Select().from(Spend.class).queryList();
        post(SpendsEvent.ON_SPEND_READED, spends);
    }

    @Override
    public void removeSpend(Spend spend) {
        spend.delete();
        post(SpendsEvent.ON_SPEND_REMOVED, Arrays.asList(spend));
    }

    private void post(int type, List<Spend> spends) {
        SpendsEvent event = new SpendsEvent();
        event.setType(type);
        event.setSpends(spends);
        eventBus.post(event);
    }
}
