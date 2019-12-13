package com.example.baristapp.ui.drinkdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.baristapp.Common.Common;
import com.example.baristapp.Model.DrinkModel;
import com.example.baristapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DrinkDetailFragment extends Fragment {

    private DrinkDetailViewModel drinkDetailsViewModel;

    private Unbinder unbinder;

    @BindView(R.id.img_drink)
    ImageView img_drink;
    @BindView(R.id.drink_name)
    TextView drink_name;
    @BindView(R.id.drink_description)
    TextView drink_description;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        drinkDetailsViewModel = ViewModelProviders.of(this).get(DrinkDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_drink_detail, container, false);
        unbinder = ButterKnife.bind(this, root);
        drinkDetailsViewModel.getMutableLiveDataDrink().observe(this, drinkModel -> {
            displayInfo(drinkModel);
        });
        return root;
    }

    private void displayInfo(DrinkModel drinkModel) {
        Glide.with(getContext()).load(drinkModel.getImage()).into(img_drink);
        drink_name.setText(new StringBuilder(drinkModel.getName()));
        drink_description.setText(new StringBuilder(drinkModel.getDescription()));

        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.selectedDrink.getName());

    }
}