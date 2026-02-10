package com.controller;

import com.exception.NullInputException;
import com.model.Funcionario;
import com.service.FuncionarioService;
import com.ui.InputReader;
import com.ui.MenuRenderer;

import java.io.IOException;
import java.util.Optional;

public class FuncionarioController {

    private final FuncionarioService fs;
    private final InputReader in;
    private final MenuRenderer out;

    public FuncionarioController(InputReader in, MenuRenderer out) {
        this.fs = new FuncionarioService();
        this.in = in;
        this.out = out;
    }

    public void add() throws IOException {
        out.showMessage("ADICIONAR NOVO FUNCIONÁRIO");

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

        out.showMessage("Matricula: ");
        String matricula = in.readLine().orElseThrow(()->new NullInputException("Matricula"));

        out.showMessage("Cargo: ");
        String cargo = in.readLine().orElseThrow(()->new NullInputException("Cargo"));

        out.showMessage("Salario: ");
        double salario = in.readDouble().orElseThrow(()->new NullInputException("Salario"));

        fs.add(new Funcionario(nome, data, sexo, cpf, telefone, matricula, cargo, salario));
        out.showMessage("Funcionário adicionado com sucesso.");
    }

    public void delete() throws IOException {
        out.showMessage("EXCLUIR FUNCIONÁRIO");
        out.showMessage("ID: ");
        Funcionario f = fs.findById(in.readInteger().orElseThrow(()->new NullInputException("ID")));

        out.showMessage("Funcionário selecionado: ");
        out.showMessage(f.toString());

        out.showMessage("Certeza que deseja excluir este funcionário? ");
        if(in.readBoolean().orElse(false)) fs.delete(f);

        System.out.println("Funcionário excluído com sucesso.");
    }

    public void listAll() throws IOException {
        out.showMessage("LISTA DE FUNCIONÁRIOS");
        for(Funcionario f : fs.listAll()){
            out.showMessage(f.toString());
        }
    }

    public void findById() throws IOException {
        out.showMessage("BUSCAR POR ID");
        out.showMessage("ID: ");
        Funcionario f = fs.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));
        out.showMessage(f.toString());
    }

    public void update() throws IOException {
        out.showMessage("ATUALIZAR FUNCIONÁRIO");
        out.showMessage("ID: ");
        Funcionario f = fs.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));

        out.showMessage("Funcionário selecionado: ");
        out.showMessage(f.toString());

        out.showUpdateMessage();

        out.showMessage("Nome: ");
        Optional<String> nome = in.readLine();
        nome.ifPresent(f::setNome);

        out.showMessage("Data de Nascimento (yyyy-MM-dd): ");
        Optional<String> data = in.readDate();
        data.ifPresent(f::setDataNascimento);

        out.showMessage("Sexo: ");
        Optional<String> sexo = in.readLine();
        sexo.ifPresent(f::setSexo);

        out.showMessage("CPF: ");
        Optional<String> cpf = in.readLine();
        cpf.ifPresent(f::setCpf);

        out.showMessage("Telefone: ");
        Optional<String> telefone = in.readLine();
        telefone.ifPresent(f::setTelefone);

        out.showMessage("Matricula: ");
        Optional<String> matricula = in.readLine();
        matricula.ifPresent(f::setMatricula);

        out.showMessage("Cargo: ");
        Optional<String> cargo = in.readLine();
        cargo.ifPresent(f::setCargo);

        out.showMessage("Salario: ");
        Optional<Double> salario = in.readDouble();
        salario.ifPresent(f::setSalario);

        fs.update(f);

        out.showMessage("Funcionário atualizado com sucesso.");
        out.showMessage(f.toString());
    }

    public void findByName() throws IOException {
        out.showMessage("BUSCAR POR NOME");
        out.showMessage("NOME: ");
        String nome = in.readLine().orElseThrow(()-> new NullInputException("Nome"));

        out.printLine();

        out.showMessage("FUNCIONÁRIOS CORRESPONDENTES");
        for(Funcionario f : fs.findByName(nome)){
            out.showMessage(f.toString());
        }
    }

    public void findByDate() throws IOException {
        out.showMessage("BUSCAR POR DATA");
        out.showMessage("DATA: ");
        String d = in.readDate().orElseThrow(()-> new NullInputException("Data"));

        out.printLine();

        out.showMessage("FUNCIONÁRIOS CORRESPONDENTES");
        for(Funcionario f : fs.findByData(d)){
            out.showMessage(f.toString());
        }
    }



}
