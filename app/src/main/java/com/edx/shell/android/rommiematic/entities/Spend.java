package com.edx.shell.android.rommiematic.entities;

import com.edx.shell.android.rommiematic.db.RoomiematicDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @author Shell_Core
 */
@Table(database = RoomiematicDatabase.class)
public class Spend extends BaseModel {

    @Column
    @PrimaryKey (autoincrement = true)
    private long id;

    @Column
    private String description;

    @Column
    private double amount;

    @Column
    private String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
