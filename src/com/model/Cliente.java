package com.model;

public class Cliente extends Pessoa {

    private boolean assinatura;

    public Cliente() {
    }

    public Cliente(int id, String nome, String dataNascimento, String sexo, String cpf, String telefone, boolean assinatura) {
        super(id, nome, dataNascimento, sexo, cpf, telefone);
        this.assinatura = assinatura;
    }

    public Cliente(String nome, String dataNascimento, String sexo, String cpf, String telefone, boolean assinatura) {
        super(nome, dataNascimento, sexo, cpf, telefone);
        this.assinatura = assinatura;
    }

    public boolean haveAssinatura() {
        return assinatura;
    }

    public void setAssinatura(boolean assinatura) {
        this.assinatura = assinatura;
    }

    @Override
    public String toString() {
        return "%s\n\tAssinatura: %s"
                .formatted(
                        super.toString(),
                        this.assinatura ? "Ativa" : "Inativa"
                );
    }
}
