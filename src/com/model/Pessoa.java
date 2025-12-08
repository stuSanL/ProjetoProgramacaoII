package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {

    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String sexo;
    private String cpf;
    private String telefone;

    public Pessoa() {}

    public Pessoa(String nome, String dataNascimento, String sexo, String cpf, String telefone) {
        this.nome = nome;
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ISO_DATE);
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Pessoa(int id, String nome, String dataNascimento, String sexo, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ISO_DATE);
        this.sexo = sexo;
        this.cpf = cpf;
        this.telefone = telefone;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ISO_DATE);
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "%d - %s\n\tNascimento: %s\tSexo: %s\n\tCPF: %s\tTelefone: %s"
                .formatted(
                        this.id,
                        this.nome,
                        this.dataNascimento,
                        this.sexo,
                        this.cpf,
                        this.telefone
                );
    }
}
