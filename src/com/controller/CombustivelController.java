package com.controller;
import com.exception.NullInputException;
import com.model.Combustivel;
import com.service.CombustivelService;
import com.ui.InputReader;
import com.ui.MenuRenderer;

import java.io.IOException;
import java.util.Optional;

public class CombustivelController {

    private final CombustivelService cs;
    private final InputReader in;
    private final MenuRenderer out;

    public CombustivelController(InputReader in, MenuRenderer out) {
        this.cs = new CombustivelService();
        this.in = in;
        this.out = out;
    }

    public void add() throws IOException {
        out.showMessage("ADICIONAR NOVO COMBUSTÍVEL");

        out.showMessage("Nome: ");
        String nome = in.readLine().orElseThrow(()->new NullInputException("Nome do combustível"));

        out.showMessage("Custo: ");
        double custo = in.readDouble().orElseThrow(()-> new NullInputException("Custo do combustível"));

        out.showMessage("Valor: ");
        double valor = in.readDouble().orElseThrow(()->new NullInputException("Valor do combustível"));

        cs.add(new Combustivel(nome, custo, valor));
        out.showMessage("Combustível criado com sucesso.");
    }

    public void delete() throws IOException {
        out.showMessage("EXCLUIR COMBUSTÍVEL");

        out.showMessage("ID: ");
        Combustivel c = cs.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));

        out.showMessage("Combustível selecionado: ");
        out.showMessage(c.toString());

        out.showMessage("Certeza que deseja excluir este combustível?");
        if(in.readBoolean().orElse(false)) cs.delete(c);

        out.showMessage("Combustível excluído com sucesso.");
    }

    public void listAll() throws IOException {
        out.showMessage("LISTA DE COMBUSTÍVEIS");
        for(Combustivel c : cs.listAll()){
            out.showMessage(c.toString());
        }
    }

    public void findById() throws IOException {
        out.showMessage("BUSCAR POR ID");
        out.showMessage("ID: ");
        Combustivel c = cs.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));
        out.showMessage(c.toString());
    }

    public void update() throws IOException {
        out.showMessage("ATUALIZAR COMBUSTIVEL");
        out.showMessage("ID: ");
        Combustivel c = cs.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));
        out.showMessage("Combustível selecionado: ");
        out.showMessage(c.toString());

        out.showUpdateMessage();

        out.showMessage("Nome: ");
        Optional<String> nome = in.readLine();
        nome.ifPresent(c::setNome);

        out.showMessage("Custo: ");
        Optional<Double>  custo = in.readDouble();
        custo.ifPresent(c::setCusto);

        out.showMessage("Valor: ");
        Optional<Double> valor = in.readDouble();
        valor.ifPresent(c::setValor);

        cs.update(c);

        out.showMessage("Combustível atualizado com sucesso:");
        out.showMessage(c.toString());
    }

    public void findByName() throws IOException {
        out.showMessage("BUSCAR POR NOME");
        out.showMessage("Nome: ");
        String nome = in.readLine().orElseThrow(()->new NullInputException("Nome"));
        out.printLine();
        out.showMessage("COMBUSTÍVEIS CORRESPONDENTES");
        for(Combustivel c : cs.findByName(nome)){
            out.showMessage(c.toString());
        }
    }

}
