package com.model;

public class Combustivel {

    private int id;
    private String nome;
    private double custo;
    private double valor; //venda

    public Combustivel() {}

    public Combustivel(String nome, double custo, double valor) {
        this.nome = nome;
        this.custo = custo;
        this.valor = valor;
    }

    public Combustivel(int id, String nome, double custo, double valor) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "%d - %s\n\tCusto: %.2f\tValor: %.2f".formatted(this.id, this.nome, this.custo, this.valor);
    }
}
