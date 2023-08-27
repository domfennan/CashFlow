package com.example.cashflow.repositorio;

import com.example.cashflow.datasource.DataSource;

public class DespesasRepositorio {

    private final DataSource dataSource = new DataSource();

    public void salvarDespesa(double valor, String descricao, String categoria) {
        DataSource dataSource = new DataSource();
        String despesaId = "despesaId"; // Substitua pelo ID desejado
        dataSource.novaDespesa(despesaId, valor, descricao, categoria);
    }
}
