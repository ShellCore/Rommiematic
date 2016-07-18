package com.edx.shell.android.rommiematic;

import android.app.Application;

import com.edx.shell.android.rommiematic.addContact.di.AddContactComponent;
import com.edx.shell.android.rommiematic.addContact.di.AddContactModule;
import com.edx.shell.android.rommiematic.addContact.di.DaggerAddContactComponent;
import com.edx.shell.android.rommiematic.addContact.ui.AddContactView;
import com.edx.shell.android.rommiematic.addSpend.di.AddSpendComponent;
import com.edx.shell.android.rommiematic.addSpend.di.AddSpendModule;
import com.edx.shell.android.rommiematic.addSpend.di.DaggerAddSpendComponent;
import com.edx.shell.android.rommiematic.addSpend.ui.AddSpendActivity;
import com.edx.shell.android.rommiematic.addSpend.ui.AddSpendView;
import com.edx.shell.android.rommiematic.domain.di.DomainModule;
import com.edx.shell.android.rommiematic.libs.di.LibsModule;
import com.edx.shell.android.rommiematic.login.di.DaggerLoginComponent;
import com.edx.shell.android.rommiematic.login.di.LoginComponent;
import com.edx.shell.android.rommiematic.login.di.LoginModule;
import com.edx.shell.android.rommiematic.login.ui.LoginView;
import com.edx.shell.android.rommiematic.main.di.DaggerMainComponent;
import com.edx.shell.android.rommiematic.main.di.MainComponent;
import com.edx.shell.android.rommiematic.main.di.MainModule;
import com.edx.shell.android.rommiematic.main.ui.MainView;
import com.edx.shell.android.rommiematic.roomies.di.DaggerRoomiesComponent;
import com.edx.shell.android.rommiematic.roomies.di.RoomiesComponent;
import com.edx.shell.android.rommiematic.roomies.di.RoomiesModule;
import com.edx.shell.android.rommiematic.roomies.ui.RoomiesActivity;
import com.edx.shell.android.rommiematic.roomies.ui.RoomiesView;
import com.edx.shell.android.rommiematic.roomies.ui.adapters.OnItemClickListener;
import com.edx.shell.android.rommiematic.spends.di.DaggerSpendsComponent;
import com.edx.shell.android.rommiematic.spends.di.SpendsComponent;
import com.edx.shell.android.rommiematic.spends.di.SpendsModule;
import com.edx.shell.android.rommiematic.spends.ui.SpendsActivity;
import com.edx.shell.android.rommiematic.spends.ui.SpendsView;
import com.firebase.client.Firebase;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * @author Shell_Core
 */
public class RommiematicApplication extends Application {

    // Constantes
    public static final String EMAIL_KEY = "email";
    public static final String SHARED_PREFERENCES_NAME = "UserPrefs";
    public static final String FIREBASE_URL = "https://rommiematic.firebaseIO.com/";
    public static final String ABOUT_URL = "http://shellcore.github.io/Perfil-personal";

    private RommiematicApplicationModule appModule;
    private DomainModule domainModule;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        initModules();
        initDatabse();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
    }

    private void initModules() {
        appModule = new RommiematicApplicationModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    private void initDatabse() {
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public static String getEmailKey() {
        return EMAIL_KEY;
    }

    public static String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent.builder()
                .rommiematicApplicationModule(appModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();
    }

    public MainComponent getMainComponent(MainView view) {
        return DaggerMainComponent.builder()
                .rommiematicApplicationModule(appModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .mainModule(new MainModule(view))
                .build();
    }

    public RoomiesComponent getRoomiesComponent(RoomiesActivity activity, RoomiesView view, OnItemClickListener clickListener) {
        return DaggerRoomiesComponent.builder()
                .rommiematicApplicationModule(appModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(activity))
                .roomiesModule(new RoomiesModule(view, clickListener))
                .build();
    }

    public AddContactComponent getAddContactComponent(AddContactView view) {
        return DaggerAddContactComponent.builder()
                .rommiematicApplicationModule(appModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .addContactModule(new AddContactModule(view))
                .build();
    }

    public SpendsComponent getSpendsComponent(SpendsActivity activity, SpendsView view, com.edx.shell.android.rommiematic.spends.ui.adapters.OnItemClickListener clickListener) {
        return DaggerSpendsComponent.builder()
                .rommiematicApplicationModule(appModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(activity))
                .spendsModule(new SpendsModule(view, clickListener))
                .build();
    }

    public AddSpendComponent getAddSpendComponent(AddSpendActivity activity, AddSpendView view) {
        return DaggerAddSpendComponent.builder()
                .rommiematicApplicationModule(appModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(activity))
                .addSpendModule(new AddSpendModule(view))
                .build();
    }
}
