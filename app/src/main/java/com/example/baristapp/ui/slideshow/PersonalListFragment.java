package com.example.baristapp.ui.slideshow;

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

public class PersonalListFragment extends Fragment {

    private PersonalListViewModel personalListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personalListViewModel =
                ViewModelProviders.of(this).get(PersonalListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personallist, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        personalListViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}