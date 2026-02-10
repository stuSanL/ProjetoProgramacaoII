package com.controller;

import com.exception.NullInputException;
import com.model.Cliente;
import com.service.ClienteService;
import com.ui.InputReader;
import com.ui.MenuRenderer;

import java.io.IOException;
import java.util.Optional;

public class ClienteController {

    private final ClienteService cs;
    private final InputReader in;
    private final MenuRenderer out;

    public ClienteController(InputReader in, MenuRenderer out) {
        this.cs = new ClienteService();
        this.in = in;
        this.out = out;
    }

    public void add() throws IOException {
        out.showMessage("ADICIONAR NOVO CLIENTE");

        out.showMessage("Nome: ");
        String nome = in.readLine().orElseThrow(()->new NullInputException("Nome do cliente"));

        out.showMessage("Data de Nascimento (yyyy-MM-dd): ");
        String data = in.readDate().orElseThrow(()->new NullInputException("Data de Nascimento"));

        out.showMessage("Sexo: ");
        String sexo = in.readLine().orElseThrow(()->new NullInputException("Sexo"));

        out.showMessage("CPF: ");
        String cpf = in.readLine().orElseThrow(()->new NullInputException("CPF do cliente"));

        out.showMessage("Telefone: ");
        String telefone = in.readLine().orElseThrow(()->new NullInputException("Telefone"));

        out.showMessage("Tem assinatura? ");
        boolean haveAssinatura = in.readBoolean().orElseThrow(()->new NullInputException("Assinatura"));

        cs.add(new Cliente(nome, data, sexo, cpf, telefone, haveAssinatura));
        out.showMessage("Cliente adicionado com sucesso.");
    }

    public void delete() throws IOException {
        out.showMessage("EXCLUIR CLIENTE");
        out.showMessage("ID: ");
        Cliente c = cs.findById(in.readInteger().orElseThrow(()->new NullInputException("ID")));

        out.showMessage("Cliente selecionado: ");
        out.showMessage(c.toString());

        out.showMessage("Certeza que deseja excluir este cliente? ");
        if(in.readBoolean().orElse(false)) cs.delete(c);

        out.showMessage("Cliente excluído com sucesso.");
    }

    public void listAll() throws IOException {
        out.showMessage("LISTA DE CLIENTES");
        for(Cliente c : cs.listAll()){
            out.showMessage(c.toString());
        }
    }

    public void findById() throws IOException {
        out.showMessage("BUSCAR POR ID");
        out.showMessage("ID: ");
        Cliente c = cs.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));
        out.showMessage(c.toString());
    }

    public void update() throws IOException {
        out.showMessage("ATUALIZAR CLIENTE");
        out.showMessage("ID: ");
        Cliente c = cs.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));

        out.showMessage("Cliente selecionado: ");
        out.showMessage(c.toString());

        out.showUpdateMessage();

        out.showMessage("Nome: ");
        Optional<String> nome = in.readLine();
        nome.ifPresent(c::setNome);

        out.showMessage("Data de Nascimento (yyyy-MM-dd): ");
        Optional<String> data = in.readDate();
        data.ifPresent(c::setDataNascimento);

        out.showMessage("Sexo: ");
        Optional<String> sexo = in.readLine();
        sexo.ifPresent(c::setSexo);

        out.showMessage("CPF: ");
        Optional<String> cpf = in.readLine();
        cpf.ifPresent(c::setCpf);

        out.showMessage("Telefone: ");
        Optional<String> telefone = in.readLine();
        telefone.ifPresent(c::setTelefone);

        out.showMessage("Tem assinatura? ");
        Optional<Boolean> assinatura = in.readBoolean();
        assinatura.ifPresent(c::setAssinatura);

        cs.update(c);

        out.showMessage("Cliente atualizado com sucesso.");
        out.showMessage(c.toString());
    }

    public void findByName() throws IOException {
        out.showMessage("BUSCAR POR NOME");
        out.showMessage("NOME: ");
        String nome = in.readLine().orElseThrow(()-> new NullInputException("Nome"));

        out.printLine();

        out.showMessage("CLIENTES CORRESPONDENTES: ");
        for(Cliente c : cs.findByName(nome)){
            out.showMessage(c.toString());
        }
    }

    public void findByData() throws IOException {
        out.showMessage("BUSCAR POR DATA");
        out.showMessage("DATA: ");
        String data = in.readDate().orElseThrow(()-> new NullInputException("DATA"));

        out.printLine();

        out.showMessage("CLIENTES CORRESPONDENTES: ");
        for(Cliente c : cs.findByData(data)){
            out.showMessage(c.toString());
        }
    }

}
