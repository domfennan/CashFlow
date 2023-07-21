package com.example.cashflow.ui.mes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cashflow.databinding.FragmentMesBinding;

public class MesFragment extends Fragment {

    private FragmentMesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MesViewModel mesViewModel =
                new ViewModelProvider(this).get(MesViewModel.class);

        binding = FragmentMesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMes;
        mesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}