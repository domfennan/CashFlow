package com.example.cashflow.models;

import java.io.Serializable;
import java.util.Objects;

public class Despesa implements Serializable {
    private String valor;
    private String descricao;
    private String categoria;
    private String data;
    private String lugar;
    private double latitude; // Adicione o campo de latitude
    private double longitude; // Adicione o campo de longitude

    public Despesa() {

    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Despesa despesa = (Despesa) o;
        return Objects.equals(valor, despesa.valor) &&
                Objects.equals(descricao, despesa.descricao) &&
                Objects.equals(categoria, despesa.categoria);
    }


    @Override
    public int hashCode() {
        return Objects.hash(valor, descricao, categoria);
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}