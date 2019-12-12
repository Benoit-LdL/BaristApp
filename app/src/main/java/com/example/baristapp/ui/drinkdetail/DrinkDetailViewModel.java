package com.example.baristapp.ui.drinkdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DrinkDetailViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DrinkDetailViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}