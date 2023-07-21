package com.example.cashflow.ui.ano;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashflow.databinding.FragmentAnoBinding;

public class AnoFragment extends Fragment {

    private FragmentAnoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AnoViewModel AnoViewModel =
                new ViewModelProvider(this).get(AnoViewModel.class);

        binding = FragmentAnoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAno;
        AnoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}