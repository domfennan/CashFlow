package com.example.cashflow.repositorio;

import com.example.cashflow.datasource.DataSourceFirebase;

public class DespesasRepositorio {

    private final DataSourceFirebase dataSourceFirebase = new DataSourceFirebase();

    public static void salvarDespesa(String usuarioID, String valor, String descricao, String categoria, String data) {

        DataSourceFirebase.novaDespesa(usuarioID, valor, descricao, categoria, data);
    }
}
