package com.edx.shell.android.rommiematic.addSpend.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.edx.shell.android.rommiematic.R;
import com.edx.shell.android.rommiematic.RommiematicApplication;
import com.edx.shell.android.rommiematic.addSpend.AddSpendPresenter;
import com.edx.shell.android.rommiematic.entities.Spend;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddSpendActivity extends AppCompatActivity implements AddSpendView {

    // Variables
    private RommiematicApplication app;
    private Spend spend;

    // Servicios
    @Inject
    AddSpendPresenter presenter;

    // Componentes
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.edt_description)
    EditText edtDescription;
    @Bind(R.id.til_description)
    TextInputLayout tilDescription;
    @Bind(R.id.edt_amoount)
    EditText edtAmoount;
    @Bind(R.id.til_amount)
    TextInputLayout tilAmount;
    @Bind(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spend);
        ButterKnife.bind(this);
        app = (RommiematicApplication) getApplication();
        setupInjection();
        setupToolbar();
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_spend, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveSpend();
                break;
            case R.id.action_cancel:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupInjection() {
        app.getAddSpendComponent(this, this)
                .inject(this);
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.addSpend);
        setSupportActionBar(toolbar);
    }

    private void saveSpend() {
        tilDescription.setError(null);
        tilAmount.setError(null);

        spend = new Spend();
        if (edtDescription.getText() == null) {
            tilDescription.setError("Este valor no debe de ser nulo");
        } else {
            spend.setDescription(edtDescription.getText().toString());
        }
        if (edtAmoount.getText() == null) {
            tilAmount.setError("Este valor no debe de ser nulo");
        } else if (Double.valueOf(edtAmoount.getText().toString()) < 0) {
            tilAmount.setError("Este valor no puede ser menor de 0");
        } else {
            spend.setAmount(Double.valueOf(edtAmoount.getText().toString()));
        }
        if (tilDescription.getError() == null && tilAmount.getError() == null) {
            presenter.addSpend(spend);
        }
    }

    @Override
    public void onSpendAdded() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSpendNotAdded() {
        Snackbar.make(container, R.string.spendnotAddedError, Snackbar.LENGTH_SHORT)
                .show();
    }
}
