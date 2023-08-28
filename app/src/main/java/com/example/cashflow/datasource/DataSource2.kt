package com.example.cashflow.datasource

import com.google.firebase.firestore.FirebaseFirestore

class DataSource2 {

    private val db = FirebaseFirestore.getInstance()

    fun novaDespesa(valor: Double, descricao: String, categoria: String){

        val despesaMap = hashMapOf(
                "depesa" to valor,
                "descricao" to descricao,
                "categoria" to categoria
        )

        db.collection("despesas").document().set(despesaMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

}
