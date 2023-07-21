package com.example.cashflow.ui.dia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashflow.databinding.FragmentDiaBinding;

public class DiaFragment extends Fragment {

    private FragmentDiaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DiaViewModel diaViewModel =
                new ViewModelProvider(this).get(DiaViewModel.class);

        binding = FragmentDiaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDia;
        diaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}