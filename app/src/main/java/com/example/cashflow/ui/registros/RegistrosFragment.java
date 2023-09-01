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
import com.example.cashflow.DataSource;
import com.example.cashflow.DespesaAdapter;
import com.example.cashflow.DetalheDespesa;
import com.example.cashflow.DespesaItemClickListener;
import com.example.cashflow.databinding.FragmentRegistrosBinding;
import com.example.cashflow.datasource.DataSourceFirebase;
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
        Log.d("RegistrosFragment", "addDataSource: Fetching despesas...");
        com.example.cashflow.datasource.DataSourceFirebase.getDespesas(usuarioID, new DataSourceFirebase.OnDespesasLoadedListener(){

            @Override
            public void onDespesasLoaded(ArrayList<Despesa> despesas) {
                Log.d("RegistrosFragment", "onDespesasLoaded: " + despesas.size() + " despesas loaded.");
                despesaAdapter.setDataSet(despesas);
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
        Intent intent = new Intent(requireContext(), DetalheDespesa.class);
        intent.putExtra("despesa", despesa);
        startActivity(intent);
    }
}
