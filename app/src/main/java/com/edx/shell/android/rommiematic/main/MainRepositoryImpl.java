package com.edx.shell.android.rommiematic.main;

import com.edx.shell.android.rommiematic.domain.FirebaseAPI;

/**
 * @author Shell_Core
 */
public class MainRepositoryImpl implements MainRepository {

    private FirebaseAPI firebaseAPI;

    public MainRepositoryImpl(FirebaseAPI firebaseAPI) {
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public String getAuthUserEmail() {
        return firebaseAPI.getAuthEmail();
    }

    @Override
    public void logout() {
        firebaseAPI.logout();
    }
}
