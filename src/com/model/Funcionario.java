package com.model;

public class Funcionario extends Pessoa {

    private String matricula;
    private String cargo;
    private double salario;

    public Funcionario() {
        super();
    }

    public Funcionario(String nome, String dataNascimento, String sexo, String cpf, String telefone, String matricula, String cargo, double salario) {
        super(nome, dataNascimento, sexo, cpf, telefone);
        this.matricula = matricula;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario(int id, String nome, String dataNascimento, String sexo, String cpf, String telefone, String matricula, String cargo, double salario) {
        super(id, nome, dataNascimento, sexo, cpf, telefone);
        this.matricula = matricula;
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "%s\n\tMatrícula: %s\tCargo: %s\n\tSalário: R$%.2f"
                .formatted(
                        super.toString(),
                        matricula, cargo, salario
                );
    }
}
