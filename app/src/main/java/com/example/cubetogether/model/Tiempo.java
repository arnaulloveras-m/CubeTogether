package com.example.cubetogether.model;

public class Tiempo {
    String data;
    int tiempo;

    public Tiempo() {}

    public Tiempo(String data, int tiempo) {
        this.data = data;
        this.tiempo = tiempo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
