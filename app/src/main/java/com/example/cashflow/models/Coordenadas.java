package com.example.cashflow.models;

public class Coordenadas {
    private double latitude;
    private double longitude;

    // Construtor vazio necessário para Firebase
    public Coordenadas() {
    }

    public Coordenadas(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}

