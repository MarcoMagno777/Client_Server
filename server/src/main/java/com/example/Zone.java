package com.example;

public class Zone {
    
    private String nome;
    private boolean stato;

    Zone(String nome){
        this.nome = nome;
        stato = false;
    }

    public String getNome() {
        return nome;
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

}
