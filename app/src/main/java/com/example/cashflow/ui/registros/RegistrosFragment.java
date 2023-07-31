package com.example.cashflow.ui.registros;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.cashflow.models.Despesa;
import java.util.ArrayList;

public class RegistrosFragment extends Fragment implements DespesaItemClickListener {

    private FragmentRegistrosBinding binding;
    private DespesaAdapter despesaAdapter;

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
        // Aqui você pode obter os dados de acordo com a lógica do seu aplicativo
        // Neste exemplo, estou usando um DataSource fictício para fins de demonstração
        ArrayList<Despesa> dataSource = DataSource.createDataSet();
        despesaAdapter.setDataSet(dataSource);
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
