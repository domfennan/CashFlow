package com.example.cashflow;

import com.example.cashflow.models.Despesa;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Despesa> createDataSet() {
        ArrayList<Despesa> list = new ArrayList<>();

        list.add(new Despesa(30.5, "Café da manhã terminal", "Alimentação", "2023-06-20", "Terminal de Ônibus"));
        list.add(new Despesa(20.0, "Transporte público barra da tijuca", "Transporte", "2023-06-21", "Barra da Tijuca"));
        list.add(new Despesa(150.0, "Roupas lacoste", "Vestuário", "2023-06-22", "Shopping Center"));
        list.add(new Despesa(80.0, "Cinema Oppenheimer", "Entretenimento", "2023-06-23", "Cinema Oppenheimer"));
        list.add(new Despesa(25.0, "Lanche 7h cantina", "Alimentação", "2023-06-24", "Cantina Escolar"));

        return list;
    }
}
