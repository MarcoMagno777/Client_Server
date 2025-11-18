package com.example;

public class Zona {
    
    private String nome;
    private boolean stato;

    Zona(String nome){
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
