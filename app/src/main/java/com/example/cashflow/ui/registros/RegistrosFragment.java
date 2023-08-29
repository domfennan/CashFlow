package com.example.cashflow.ui.registros;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.cashflow.datasource.DataSource;
import com.example.cashflow.DespesaAdapter;
import com.example.cashflow.DetalheDespesa;
import com.example.cashflow.DespesaItemClickListener;
import com.example.cashflow.databinding.FragmentRegistrosBinding;
import com.example.cashflow.models.Despesa;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Objects;

public class RegistrosFragment extends Fragment implements DespesaItemClickListener {

    private FragmentRegistrosBinding binding;
    private DespesaAdapter despesaAdapter;
    String usuarioID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegistrosViewModel registrosViewModel =
                new ViewModelProvider(this).get(RegistrosViewModel.class);

        binding = FragmentRegistrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initRecyclerView();
        addDataSource();

        return root;
    }

    private void initRecyclerView() {
        despesaAdapter = new DespesaAdapter();
        despesaAdapter.setDespesaItemClickListener(this);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.setAdapter(despesaAdapter);
    }

    private void addDataSource() {
        usuarioID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        com.example.cashflow.datasource.DataSource.getDespesas(usuarioID, new DataSource.OnDespesasLoadedListener() {
            @Override
            public void onDespesasLoaded(ArrayList<Despesa> despesas) {
                if (!despesas.isEmpty()) {
                    despesaAdapter.setDataSet(despesas);
                    // Mostrar a RecyclerView e ocultar uma mensagem de lista vazia
                    binding.recyclerview.setVisibility(View.VISIBLE);
                    binding.toolbar.setVisibility(View.GONE); // Ocultar a mensagem de lista vazia
                    binding.spinnerTimeFrame.setVisibility(View.GONE); // Ocultar a mensagem de lista vazia

                } else {
                    // Mostrar uma mensagem de lista vazia e ocultar a RecyclerView
                    binding.recyclerview.setVisibility(View.GONE);
                    binding.toolbar.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDespesaItemClick(Despesa despesa) {
        // Aqui você pode abrir a tela DetalheDespesa e passar os dados da despesa
        // Exemplo usando Intent:
        //Intent intent = new Intent(requireContext(), DetalheDespesa.class);
        //intent.putExtra("despesa", despesa);
        //startActivity(intent);
    }
}
