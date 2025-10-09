package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Combustivel {
    public String nome;
    public double custo;
    public double precoVenda;

    public Combustivel(String nome, double custo, double precoVenda) {
        this.nome = nome;
        this.custo = custo;
        this.precoVenda = precoVenda;
    } public Combustivel(){}

    public void cadastrarCombustivel() throws IOException {
        File combustiveis = new File("data/combustiveis.csv");
        FileWriter fw = new FileWriter(combustiveis);
        fw.write(this.nome + "," + this.custo + "," + this.precoVenda + "\n");
        fw.close();
        Tanque tanque = new Tanque();
    }
}
