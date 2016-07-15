package com.edx.shell.android.rommiematic.spends;

import com.edx.shell.android.rommiematic.entities.Spend;

/**
 * @author Shell_Core
 */
public class SpendsInteractorImpl implements SpendsInteractor {

    // Servicios
    private SpendsRepository repository;

    public SpendsInteractorImpl(SpendsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getSpends() {
        repository.getSavedSpends();
    }

    @Override
    public void removeSpend(Spend spend) {
        repository.removeSpend(spend);
    }
}
