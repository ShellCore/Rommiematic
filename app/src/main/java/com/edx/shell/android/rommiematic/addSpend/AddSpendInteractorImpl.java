package com.edx.shell.android.rommiematic.addSpend;

import com.edx.shell.android.rommiematic.entities.Spend;

/**
 * @author Shell_Core
 */
public class AddSpendInteractorImpl implements AddSpendInteractor {

    // Servicios
    private AddSpendRepository repository;

    public AddSpendInteractorImpl(AddSpendRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Spend spend) {
        repository.addSpend(spend);
    }
}
