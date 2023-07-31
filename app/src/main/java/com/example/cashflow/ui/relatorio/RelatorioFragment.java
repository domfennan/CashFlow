package com.example.cashflow.ui.relatorio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashflow.databinding.FragmentRelatorioBinding;

public class RelatorioFragment extends Fragment {

    private FragmentRelatorioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RelatorioViewModel relatorioViewModel =
                new ViewModelProvider(this).get(RelatorioViewModel.class);

        binding = FragmentRelatorioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSemana;
        relatorioViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}