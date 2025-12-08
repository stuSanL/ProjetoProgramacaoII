package com.model;

public class Tanque {

    private int id;
    private Combustivel combustivel;
    private double capacidade;
    private double volumeAtual;

    public Tanque() {}

    public Tanque(Combustivel combustivel, double capacidade, double volumeAtual) {
        this.combustivel = combustivel;
        this.capacidade = capacidade;
        this.volumeAtual = volumeAtual;
    }

    public Tanque(int id, Combustivel combustivel, double capacidade, double volumeAtual) {
        this.id = id;
        this.combustivel = combustivel;
        this.capacidade = capacidade;
        this.volumeAtual = volumeAtual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Combustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustivel combustivel) {
        this.combustivel = combustivel;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }

    public double getVolumeAtual() {
        return volumeAtual;
    }

    public void setVolumeAtual(double volumeAtual) {
        this.volumeAtual = volumeAtual;
    }

    @Override
    public String toString() {
        return "%d - %s (%.1fL / %.1fL)"
                .formatted(this.id, this.combustivel.getNome(), this.capacidade, this.volumeAtual);
    }
}
