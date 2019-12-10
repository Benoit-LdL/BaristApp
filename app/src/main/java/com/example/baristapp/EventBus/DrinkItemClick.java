package com.example.baristapp.EventBus;

import com.example.baristapp.Model.DrinkModel;

public class DrinkItemClick {
    private boolean success;
    private DrinkModel drinkModel;

    public DrinkItemClick(boolean success, DrinkModel drinkModel) {
        this.success = success;
        this.drinkModel = drinkModel;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DrinkModel getDrinkModel() {
        return drinkModel;
    }

    public void setDrinkModel(DrinkModel drinkModel) {
        this.drinkModel = drinkModel;
    }
}
