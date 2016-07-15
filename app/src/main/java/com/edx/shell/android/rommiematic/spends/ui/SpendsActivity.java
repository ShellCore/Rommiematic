package com.edx.shell.android.rommiematic.spends.ui;

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
import com.edx.shell.android.rommiematic.entities.Spend;
import com.edx.shell.android.rommiematic.spends.SpendsPresenter;
import com.edx.shell.android.rommiematic.spends.ui.adapters.OnItemClickListener;
import com.edx.shell.android.rommiematic.spends.ui.adapters.SpendsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpendsActivity extends AppCompatActivity implements SpendsView, OnItemClickListener {

    // Variables
    private RommiematicApplication app;

    // Servicios
    @Inject
    SpendsPresenter presenter;
    @Inject
    SpendsAdapter adapter;

    // Componentes
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @Bind(R.id.rec_spends)
    RecyclerView recSpends;
    @Bind(R.id.add)
    FloatingActionButton add;
    @Bind(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spends);
        ButterKnife.bind(this);
        app = (RommiematicApplication) getApplication();
        setupInjection();
        setupToolbar();
        setupRecyclerView();
        presenter.onCreate();
        presenter.getSpends();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        app.getSpendsComponent(this, this, this)
                .inject(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(getString(R.string.spends_title));
        setSupportActionBar(toolbar);
    }

    private void setupRecyclerView() {
        recSpends.setLayoutManager(new LinearLayoutManager(this));
        recSpends.setAdapter(adapter);
    }

    @Override
    public void onSpendAdded(Spend spend) {
        adapter.add(spend);
    }

    @Override
    public void onSpendRemoved(Spend spend) {
        adapter.remove(spend);
    }

    @Override
    public void setSpends(List<Spend> data) {
        adapter.setSpends(data);
    }

    @Override
    public void onItemClick(Spend spend) {
        Snackbar.make(container, "Pushed: " + spend.getDescription(), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onDelete(Spend spend) {
        presenter.removeSpend(spend);
    }

    @OnClick(R.id.add)
    @Override
    public void addSpend() {
        // TODO Realizar cuadro de dialogo para agregar un gasto
    }
}
