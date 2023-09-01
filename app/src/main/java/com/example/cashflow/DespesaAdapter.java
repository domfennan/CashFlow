package com.example.cashflow;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cashflow.databinding.ResItemDespesaBinding;
import com.example.cashflow.models.Despesa;

import java.util.List;

public class DespesaAdapter extends RecyclerView.Adapter<DespesaAdapter.DespesaViewHolder> {

    private List<Despesa> items;
    private DespesaItemClickListener despesaItemClickListener;

    public void setDataSet(List<Despesa> despesas) {
        this.items = despesas;
        notifyDataSetChanged(); // Adicione esta linha para notificar o adapter sobre as mudanças
    }

    public void setDespesaItemClickListener(DespesaItemClickListener listener) {
        this.despesaItemClickListener = listener;
    }

    @NonNull
    @Override
    public DespesaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("DespesaAdapter", "onCreateViewHolder");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ResItemDespesaBinding binding = ResItemDespesaBinding.inflate(layoutInflater, parent, false);
        return new DespesaViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull DespesaViewHolder holder, int position) {
        holder.bind(items.get(position));
        Log.d("DespesaAdapter", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        Log.d("DespesaAdapter", "getItemCount: " + (items != null ? items.size() : 0));
        return items != null ? items.size() : 0;
    }

    public class DespesaViewHolder extends RecyclerView.ViewHolder {

        private final ResItemDespesaBinding binding;

        public DespesaViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ResItemDespesaBinding.bind(itemView);
        }

        public void bind(Despesa despesa) {
            Log.d("DespesaAdapter", "Categoria: " + despesa.getCategoria());
            Log.d("DespesaAdapter", "Descrição: " + despesa.getDescricao());
            Log.d("DespesaAdapter", "Valor: " + despesa.getValor());

            binding.categoria.setText(despesa.getCategoria());
            binding.descricao.setText(despesa.getDescricao());
            String valorFormatado = "-R$ " + despesa.getValor();
            binding.valor.setText(valorFormatado);

            itemView.setOnClickListener(view -> {
                if (despesaItemClickListener != null) {
                    despesaItemClickListener.onDespesaItemClick(despesa);
                }
            });
        }
    }


}
