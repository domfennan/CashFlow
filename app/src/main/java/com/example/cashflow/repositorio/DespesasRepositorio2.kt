package com.example.cashflow.repositorio

import com.example.cashflow.datasource.DataSource2

class DespesasRepositorio2() {

    private val dataSource2 = DataSource2()

    fun salvarDespesa(valor: Double, descricao: String, categoria: String){
        dataSource2.novaDespesa(valor,descricao,categoria)
    }
}