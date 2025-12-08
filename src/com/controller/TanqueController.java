package com.controller;

import com.dao.CombustivelDAO;
import com.exception.NullInputException;
import com.exception.ResourceNotFoundException;
import com.model.Tanque;
import com.service.TanqueService;
import com.ui.InputReader;
import com.ui.MenuRenderer;

import java.io.IOException;
import java.util.Optional;

public class TanqueController {

    private final TanqueService ts;
    private final InputReader in;
    private final MenuRenderer out;

    public TanqueController(InputReader in, MenuRenderer out) {
        this.ts = new TanqueService();
        this.in = in;
        this.out = out;
    }

    public void add() throws IOException {
        CombustivelDAO cDAO = new CombustivelDAO();
        out.showMessage("ADICIONAR NOVO TANQUE");

        out.showMessage("ID do combustível: ");
        int id = in.readInteger().orElseThrow(()->new NullInputException("ID do combustível"));

        out.showMessage("Capacidade: ");
        double capacidade = in.readDouble().orElseThrow(()-> new NullInputException("Capacidade"));

        out.showMessage("Volume atual: ");
        double volume = in.readDouble().orElseThrow(()->new NullInputException("Volume atual"));

        ts.add(new Tanque(cDAO.findByID(id), capacidade, volume));
        out.showMessage("Combustível adicionado com sucesso.");
    }

    public void delete() throws IOException {
        out.showMessage("EXCLUIR TANQUE");

        out.showMessage("ID: ");
        Tanque t = ts.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));

        out.showMessage("Tanque selecionado: ");
        out.showMessage(t.toString());

        out.showMessage("Certeza que deseja excluir este tanque?");
        if(in.readBoolean().orElse(false)) ts.delete(t);

        out.showMessage("Tanque excluído com sucesso.");
    }

    public void listAll() throws IOException {
        out.showMessage("LISTA DE TANQUES");
        for(Tanque t : ts.listAll()){
            out.showMessage(t.toString());
        }
    }

    public void findById() throws IOException {
        out.showMessage("BUSCAR POR ID");
        out.showMessage("ID: ");
        Tanque t = ts.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));
        out.showMessage(t.toString());
    }

    public void update() throws IOException {
        CombustivelDAO cDAO = new CombustivelDAO();
        out.showMessage("ATUALIZAR TANQUE");
        out.showMessage("ID: ");
        Tanque t = ts.findById(in.readInteger().orElseThrow(() -> new NullInputException("ID")));
        out.showMessage("Tanque selecionado: ");
        out.showMessage(t.toString());

        out.showUpdateMessage();

        out.showMessage("ID do combustível: ");
        Optional<Integer> id = in.readInteger();
        id.ifPresent( i -> {
            try {
                t.setCombustivel(cDAO.findByID(i));
            } catch (IOException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        });

        out.showMessage("Capacidade: ");
        Optional<Double>  capacidade = in.readDouble();
        capacidade.ifPresent(t::setCapacidade);

        out.showMessage("Volume atual: ");
        Optional<Double> volumeAtual = in.readDouble();
        volumeAtual.ifPresent(t::setVolumeAtual);

        ts.update(t);

        out.showMessage("Tanque atualizado com sucesso:");
        out.showMessage(t.toString());
    }

    public void findByCombustivel() throws IOException {
        CombustivelDAO cDAO = new CombustivelDAO();
        out.showMessage("BUSCAR POR COMBUSTÍVEL");
        out.showMessage("ID: ");
        int id = in.readInteger().orElseThrow(() -> new NullInputException("ID do combustivel"));
        out.printLine();
        out.showMessage("TANQUES CORRESPONDENTES");
        for(Tanque t : ts.findByCombustivel(cDAO.findByID(id))){
            out.showMessage(t.toString());
        }
    }
}
