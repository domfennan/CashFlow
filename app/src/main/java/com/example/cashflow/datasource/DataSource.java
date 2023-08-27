package com.example.cashflow.datasource;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DataSource {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void novaDespesa(String despesaID, double valor, String descricao, String categoria) {

        HashMap<String, Object> despesaMap = new HashMap<>();
        despesaMap.put("despesa", valor);
        despesaMap.put("descricao", descricao);
        despesaMap.put("categoria", categoria);

        db.collection("despesas").document(despesaID).set(despesaMap)
                .addOnCompleteListener(task -> {
                    // Ação a ser executada em caso de sucesso
                })
                .addOnFailureListener(e -> {
                    // Tratamento de falha
                });
    }
}
