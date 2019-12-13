package com.example.baristapp.ui.drinkdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baristapp.Common.Common;
import com.example.baristapp.Model.DrinkModel;

public class DrinkDetailViewModel extends ViewModel {

    private MutableLiveData<DrinkModel> mutableLiveDataDrink;

    public DrinkDetailViewModel() {

    }

    public MutableLiveData<DrinkModel> getMutableLiveDataDrink()
    {
        if (mutableLiveDataDrink == null){
            mutableLiveDataDrink = new MutableLiveData<>();
        }
        mutableLiveDataDrink.setValue(Common.selectedDrink);
        return mutableLiveDataDrink;
    }
}