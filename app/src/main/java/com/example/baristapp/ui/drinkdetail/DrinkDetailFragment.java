package com.example.baristapp.ui.drinkdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.baristapp.R;

public class DrinkDetailFragment extends Fragment {

    private DrinkDetailViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(DrinkDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_drink_detail, container, false);

        //toolsViewModel.getText().observe(this, new Observer<String>() {

        //});
        return root;
    }
}