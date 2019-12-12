package com.example.baristapp.ui.drinklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baristapp.Adapter.MyDrinkListAdapter;
import com.example.baristapp.Common.Common;
import com.example.baristapp.Model.DrinkModel;
import com.example.baristapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DrinkListFragment extends Fragment {

    private DrinkListViewModel drinkViewModel;

    Unbinder unbinder;
    @BindView(R.id.recycler_drink_list)
    RecyclerView recycler_drink_list;

    LayoutAnimationController layoutAnimationController;
    MyDrinkListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        drinkViewModel = ViewModelProviders.of(this).get(DrinkListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_drink_list, container, false);
        unbinder = ButterKnife.bind(this, root);
        initViews();
        drinkViewModel.getMutableLiveDataDrinkList().observe(this, drinkModels -> {
            adapter = new MyDrinkListAdapter(getContext(), drinkModels);
            recycler_drink_list.setAdapter(adapter);
            recycler_drink_list.setLayoutAnimation(layoutAnimationController);
        });
        return root;
    }

    private void initViews() {
        
        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.categorySelected.getName());

        recycler_drink_list.setHasFixedSize(true);
        recycler_drink_list.setLayoutManager(new LinearLayoutManager(getContext()));

        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_item_from_left);
    }
}