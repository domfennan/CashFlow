package com.example.cashflow.repositorio;

import com.example.cashflow.datasource.DataSource;

public class DespesasRepositorio {

    private final DataSource dataSource = new DataSource();

    public static void salvarDespesa(String valor, String descricao, String categoria, String data) {
        DataSource dataSource = new DataSource();
        String despesaId = "despesaId"; // Substitua pelo ID desejado
        dataSource.novaDespesa(despesaId, valor, descricao, categoria, data);
    }
}
