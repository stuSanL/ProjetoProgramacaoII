package com.model;

import java.io.*;
import java.util.Arrays;

public class Tanque {

    long id;
    public Combustivel combustivel;
    public double combustivelDisponivel;
    public double capacidade;

    private String TANQUES = "src/com/data/tanques.csv";
    private Metodos

    public Tanque(Combustivel combustivel, double capacidade){
        this.combustivel = combustivel;
        this.capacidade = capacidade;
        this.id = m.gerar(TANQUES);
    } public Tanque(){}

    public void cadastrar() throws IOException {
        FileWriter fw = new FileWriter(TANQUES);
        fw.write(id+","+combustivel.nome+","+combustivelDisponivel+","+capacidade+"\n");
        fw.close();
    }

    public void atualizarCombustivel(long id, Combustivel c) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(TANQUES));
        StringBuilder linha = new StringBuilder();
        br.lines().forEach(l -> {
            if(l.contains(String.valueOf(id))) linha.append(l);
        });
        br.close();
        String[] linhaEdit = linha.toString().split(",");
        Arrays.stream(linhaEdit).toList().forEach(l -> {
            System.out.println(String.valueOf(l));
        });
        /*linhaEdit[1] = c.nome;
        FileWriter fw = new FileWriter(TANQUES);
        fw.write(linha.toString());*/
    }

}
