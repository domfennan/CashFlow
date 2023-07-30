package com.example.cashflow;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDespesa {
    private static CategoriaDespesa instance;
    private List<String> categoriasDisponiveis = new ArrayList<>();

    private CategoriaDespesa() {
        // Adicione as categorias disponíveis à lista
        categoriasDisponiveis.add("Alimentação");
        categoriasDisponiveis.add("Transporte");
        categoriasDisponiveis.add("Compras");
        categoriasDisponiveis.add("Carro");
        categoriasDisponiveis.add("Educação");
        categoriasDisponiveis.add("Família");
        categoriasDisponiveis.add("Lazer");
        categoriasDisponiveis.add("Saúde");
        categoriasDisponiveis.add("Vestuário");
        categoriasDisponiveis.add("Outros");
    }

    public static synchronized CategoriaDespesa getInstance() {
        if (instance == null) {
            instance = new CategoriaDespesa();
        }
        return instance;
    }

    public List<String> getCategoriasDisponiveis() {
        return categoriasDisponiveis;
    }
}


