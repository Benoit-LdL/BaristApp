package com.example.baristapp.Callback;

import com.example.baristapp.Model.BestDealModel;
import com.example.baristapp.Model.CategoryModel;

import java.util.List;

public interface ICategoryCallbackListener {
    void onCategoryLoadSucces(List<CategoryModel> categoryModelsList);
    void onCategoryLoadFailed(String message);
}
