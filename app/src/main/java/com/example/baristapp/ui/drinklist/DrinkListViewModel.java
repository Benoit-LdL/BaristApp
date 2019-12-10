package com.example.baristapp.ui.drinklist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baristapp.Common.Common;
import com.example.baristapp.Model.DrinkModel;

import java.util.List;

public class DrinkListViewModel extends ViewModel {

    private MutableLiveData<List<DrinkModel>> mutableLiveDataDrinkList;

    public DrinkListViewModel() {

    }

    public MutableLiveData<List<DrinkModel>> getMutableLiveDataDrinkList() {
        if (mutableLiveDataDrinkList ==  null)
            mutableLiveDataDrinkList = new MutableLiveData<>();
        mutableLiveDataDrinkList.setValue(Common.categorySelected.getDrinks());
        return mutableLiveDataDrinkList;
    }
}