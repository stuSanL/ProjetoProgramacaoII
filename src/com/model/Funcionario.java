package com.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class Funcionario {
    String nome;
    String cpf;
    Date dataCadastro;

    private String FUNCIONARIOS = "funcionarios.csv";

    public Funcionario(String nome, String cpf, Date dataCadastro) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
    }

    public void cadastrarFuncionario() throws IOException {
        File funcionarios = new File(FUNCIONARIOS);
        FileWriter fw = new FileWriter(funcionarios, true);
        fw.write(this.nome + "," + this.cpf + "," + this.dataCadastro + "\n");
        fw.close();
    }

}
