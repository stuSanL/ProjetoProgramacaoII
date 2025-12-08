package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    private int id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Tanque tanque;
    private double litragem;
    private double valor;
    private LocalDate dataVenda;

    public Venda(int id, Cliente cliente, Funcionario funcionario, Tanque tanque, double litragem, double valor,  String dataVenda) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.tanque = tanque;
        this.litragem = litragem;
        this.valor = valor;
        this.dataVenda = LocalDate.parse(dataVenda, DateTimeFormatter.ISO_DATE);
    }

    public Venda(Cliente cliente, Funcionario funcionario, Tanque tanque, double litragem, double valor, LocalDate dataVenda) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.tanque = tanque;
        this.litragem = litragem;
        this.valor = valor;
        this.dataVenda = dataVenda;
    }

    public Venda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    public double getLitragem() {
        return litragem;
    }

    public void setLitragem(double litragem) {
        this.litragem = litragem;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = LocalDate.parse(dataVenda, DateTimeFormatter.ISO_DATE);
    }

    @Override
    public String toString() {
        return "%d - %.2f (%.1fL)\n"
                .formatted(getId(), getValor(), getLitragem())
                + "\t CL: %d - %s\n"
                .formatted(getCliente().getId(), getCliente().getNome())
                + "\t FC: %d - %s"
                .formatted(getFuncionario().getId(), getFuncionario().getNome())
                + "\t Tan: %d - %s\n"
                .formatted(getTanque().getId(), getTanque().getCombustivel().getNome())
                + "\tData: %s".formatted(getDataVenda());
    }
}
