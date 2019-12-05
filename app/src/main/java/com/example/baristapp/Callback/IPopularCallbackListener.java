package com.example.baristapp.Callback;

import com.example.baristapp.Model.PopularCategoryModel;

import java.util.List;

public interface IPopularCallbackListener {
    void onPopularLoadSucces(List<PopularCategoryModel>popularCategoryModels);
    void onPopularLoadFailed(String message);
}
