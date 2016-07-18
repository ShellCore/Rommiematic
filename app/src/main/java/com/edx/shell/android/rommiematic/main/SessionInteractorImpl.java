package com.edx.shell.android.rommiematic.main;

/**
 * @author Shell_Core
 */
public class SessionInteractorImpl implements SessionInteractor {

    // Servicios
    private MainRepository repository;

    public SessionInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getAuthUserEmail();
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
