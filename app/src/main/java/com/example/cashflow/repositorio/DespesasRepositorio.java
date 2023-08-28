package com.example.cashflow.repositorio;

import com.example.cashflow.datasource.DataSource;

public class DespesasRepositorio {

    private final DataSource dataSource = new DataSource();

    public static void salvarDespesa(String usuarioID, String valor, String descricao, String categoria, String data) {

        DataSource.novaDespesa(usuarioID, valor, descricao, categoria, data);
    }
}
