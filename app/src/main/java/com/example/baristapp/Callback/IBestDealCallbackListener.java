package com.example.baristapp.Callback;

import com.example.baristapp.Model.BestDealModel;
import com.example.baristapp.Model.PopularCategoryModel;

import java.util.List;

public interface IBestDealCallbackListener {
    void onBestDealLoadSucces(List<BestDealModel> bestDealModels);
    void onBestDealLoadFailed(String message);
}
