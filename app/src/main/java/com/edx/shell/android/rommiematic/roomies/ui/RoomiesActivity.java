package com.edx.shell.android.rommiematic.roomies.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.edx.shell.android.rommiematic.R;
import com.edx.shell.android.rommiematic.RommiematicApplication;
import com.edx.shell.android.rommiematic.addContact.ui.AddContactFragment;
import com.edx.shell.android.rommiematic.entities.User;
import com.edx.shell.android.rommiematic.roomies.RoomiesPresenter;
import com.edx.shell.android.rommiematic.roomies.ui.adapters.OnItemClickListener;
import com.edx.shell.android.rommiematic.roomies.ui.adapters.RoomiesAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomiesActivity extends AppCompatActivity implements RoomiesView, OnItemClickListener {

    // Variables
    private RommiematicApplication app;

    // Servicios
    @Inject
    RoomiesPresenter presenter;
    @Inject
    RoomiesAdapter adapter;

    // Componentes
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @Bind(R.id.rec_roomies)
    RecyclerView recRoomies;
    @Bind(R.id.add)
    FloatingActionButton add;
    @Bind(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomies);
        ButterKnife.bind(this);
        app = (RommiematicApplication) getApplication();
        setupInjection();
        setupToolbar();
        setupRecyclerView();
        presenter.onCreate();

    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        app.getRoomiesComponent(this, this, this)
                .inject(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        recRoomies.setLayoutManager(new LinearLayoutManager(this));
        recRoomies.setAdapter(adapter);
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        Snackbar.make(container, "Pushed: " + user.getEmail(), Snackbar.LENGTH_SHORT)
                .show();
    }

    @OnClick(R.id.add)
    public void addContact() {
        new AddContactFragment().show(getSupportFragmentManager(), getString(R.string.addContact_message_title));
    }

}
