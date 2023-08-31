package com.example.cashflow.datasource;

import com.example.cashflow.models.Despesa;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class DataSourceFirebase {

    public static void novaDespesa(String usuarioID, String valor, String descricao, String categoria, String data) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        HashMap<String, Object> despesaMap = new HashMap<>();
        despesaMap.put("despesa", valor);
        despesaMap.put("descricao", descricao);
        despesaMap.put("categoria", categoria);
        despesaMap.put("data", data);

        db.collection("Usuarios")
                .document(usuarioID)
                .collection("Despesas")
                .add(despesaMap) // Use o método 'add' para criar um novo documento
                .addOnCompleteListener(task -> {
                    // Ação a ser executada em caso de sucesso
                })
                .addOnFailureListener(e -> {
                    // Tratamento de falha
                });
    }
    public static void getDespesas(String usuarioID, OnDespesasLoadedListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Usuarios")
                .document(usuarioID)
                .collection("Despesas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<Despesa> despesas = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Mapeie o documento para um objeto Despesa
                            Despesa despesa = new Despesa();
                            despesa.setValor((String) document.get("despesa"));
                            despesa.setDescricao((String) document.get("descricao"));
                            despesa.setCategoria((String) document.get("categoria"));
                            despesa.setData((String) document.get("data"));
                            // Outros campos como lugar, se aplicável
                            despesas.add(despesa);
                        }
                        listener.onDespesasLoaded(despesas);
                    } else {
                        // Tratamento de falha
                    }
                });
    }

    public interface OnDespesasLoadedListener {
        void onDespesasLoaded(ArrayList<Despesa> despesas);
    }
}


