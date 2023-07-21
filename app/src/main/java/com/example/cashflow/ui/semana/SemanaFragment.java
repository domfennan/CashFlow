package com.example.cashflow.ui.semana;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashflow.databinding.FragmentSemanaBinding;

public class SemanaFragment extends Fragment {

    private FragmentSemanaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SemanaViewModel semanaViewModel =
                new ViewModelProvider(this).get(SemanaViewModel.class);

        binding = FragmentSemanaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSemana;
        semanaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}