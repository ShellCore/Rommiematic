package com.edx.shell.android.rommiematic.main.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.edx.shell.android.rommiematic.costs.CostActivity;
import com.edx.shell.android.rommiematic.R;
import com.edx.shell.android.rommiematic.RommiematicApplication;
import com.edx.shell.android.rommiematic.roomies.ui.RoomiesActivity;
import com.edx.shell.android.rommiematic.login.ui.LoginActivity;
import com.edx.shell.android.rommiematic.main.MainPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    // Variables
    private RommiematicApplication app;

    // Servicios
    @Inject
    MainPresenter presenter;

    // Componentes
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.img_roomies)
    ImageView imgRoomies;
    @Bind(R.id.img_cost)
    ImageView imgCost;
    @Bind(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (RommiematicApplication) getApplication();
        setupInjection();
        setupToolBar();
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                break;
            case R.id.action_about:
                doAbout();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setupToolBar() {
        toolbar.setTitle(presenter.getCurrentEmail());
        setSupportActionBar(toolbar);
    }

    private void setupInjection() {
        app.getMainComponent(this)
                .inject(this);
    }

    private void logout() {
        presenter.signOff();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void doAbout() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(RommiematicApplication.ABOUT_URL));
        startActivity(intent);
    }

    @OnClick(R.id.img_roomies)
    @Override
    public void handleRoomies() {
        startActivity(new Intent(this, RoomiesActivity.class));
    }

    @OnClick(R.id.img_cost)
    @Override
    public void handleCost() {
        startActivity(new Intent(this, CostActivity.class));
    }
}
