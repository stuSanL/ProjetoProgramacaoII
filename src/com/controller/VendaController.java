package com.controller;

import com.exception.NullInputException;
import com.exception.ResourceNotFoundException;
import com.model.Cliente;
import com.model.Funcionario;
import com.model.Tanque;
import com.model.Venda;
import com.service.ClienteService;
import com.service.FuncionarioService;
import com.service.TanqueService;
import com.service.VendaService;
import com.ui.InputReader;
import com.ui.MenuRenderer;

import java.io.IOException;
import java.util.Optional;

public class VendaController {

    private final VendaService vs;
    private final InputReader in;
    private final MenuRenderer out;

    public VendaController(InputReader in, MenuRenderer out) {
        this.vs = new VendaService();
        this.in = in;
        this.out = out;
    }

    public void add() throws IOException {
        ClienteService cs = new ClienteService();
        FuncionarioService fs = new FuncionarioService();
        TanqueService ts = new TanqueService();
        Venda v = new Venda();

        out.showMessage("ADICIONAR NOVA VENDA");

        out.showMessage("ID do cliente: ");
        try {
            v.setCliente(cs.findById(in.readInteger()
                    .orElseThrow(() -> new NullInputException("ID do cliente"))
            ));
        } catch (IOException e) {
            out.showError(e.getMessage());
        }

        out.showMessage("ID do Funcionário: ");
        v.setFuncionario(fs.findById(in.readInteger()
                .orElseThrow(() -> new NullInputException("ID do funcionário"))
        ));

        out.showMessage("ID do Tanque: ");
        v.setTanque(ts.findById(in.readInteger()
                .orElseThrow(() -> new NullInputException("ID do tanque"))
        ));

        out.showMessage("Litragem: ");
        v.setLitragem(in.readDouble()
                .orElseThrow(() -> new NullInputException("Litragem")
        ));

        vs.add(v);

        out.showMessage("Venda adicionada com sucesso.");
    }

    public void delete() throws IOException {
        System.out.println("EXCLUIR VENDA");

        out.showMessage("ID: ");
        Venda v = vs.findById(in.readInteger()
                .orElseThrow(() -> new NullInputException("ID"))
        );

        System.out.println("Venda selecionada: ");
        out.showMessage(v.toString());
        out.showMessage("");

        out.showMessage("Certeza de que deseja excluir essa venda?");
        if(in.readBoolean().orElse(false)) vs.delete(v);

        out.showMessage("Venda excluída com sucesso.");

    }

    public void listAll() throws IOException {
        out.showMessage("LISTA DE VENDAS");
        for(Venda v : vs.listAll()){
            out.showMessage(v.toString());
        }
    }

    public void findById() throws IOException {
        out.showMessage("BUSCAR POR ID");
        out.showMessage("ID: ");
        Venda v = vs.findById(in.readInteger()
                .orElseThrow(() -> new NullInputException("ID"))
        );
        out.showMessage(v.toString());
    }

    public void update() throws IOException {
        ClienteService cs = new ClienteService();
        FuncionarioService fs = new FuncionarioService();
        TanqueService ts = new TanqueService();
        out.showMessage("ATUALIZAR VENDA");
        out.showMessage("ID: ");
        Venda v = vs.findById(in.readInteger()
                .orElseThrow(() -> new NullInputException("ID"))
        );

        out.showMessage("Venda selecionada: ");
        out.showMessage(v.toString());

        out.showUpdateMessage();

        out.showMessage("ID do Cliente: ");
        Optional<Integer> idCliente = in.readInteger();
        idCliente.ifPresent(i -> {
            try {
                v.setCliente(cs.findById(i));
            } catch (IOException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        });

        out.showMessage("ID do Funcionário: ");
        Optional<Integer> idFuncionario = in.readInteger();
        idFuncionario.ifPresent(i -> {
            try {
                v.setFuncionario(fs.findById(i));
            } catch (IOException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        });

        out.showMessage("ID do Tanque: ");
        Optional<Integer> idTanque = in.readInteger();
        idTanque.ifPresent(i -> {
            try {
                v.setTanque(ts.findById(i));
            } catch (IOException e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        });

        out.showMessage("Litragem: ");
        Optional<Double> litragem = in.readDouble();
        litragem.ifPresent(l->{
            v.setLitragem(l);
            vs.atualizarValor(v);
        });

        out.showMessage("Data: ");
        Optional<String> data = in.readLine();
        data.ifPresent(v::setDataVenda);

        vs.update(v);

        out.showMessage("Venda atualizada com sucesso.");
    }

    public void findByCliente() throws IOException {
        ClienteService cs = new ClienteService();
        out.showMessage("BUSCAR POR CLIENTE");
        out.showMessage("ID do cliente: ");
        Cliente c = cs.findById(in.readInteger()
                .orElseThrow(()-> new NullInputException("Id do Cliente"))
        );
        out.showMessage("Cliente selecionado: ");
        out.showMessage(c.toString());
        out.printLine();
        out.showMessage("Lista de Vendas: ");
        for(Venda v : vs.findByCliente(c)){
            out.showMessage(v.toString());
        }
    }

    public void findByFuncionario() throws IOException {
        FuncionarioService fs = new FuncionarioService();
        out.showMessage("BUSCAR POR FUNCIONÁRIO");
        out.showMessage("ID do funcionário: ");
        Funcionario f = fs.findById(in.readInteger()
                .orElseThrow(()-> new NullInputException("ID do Funcionário"))
        );
        out.showMessage("Funcionário selecionado: ");
        out.showMessage(f.toString());
        out.printLine();
        out.showMessage("Lista de Vendas: ");
        for(Venda v : vs.findByFuncionario(f)){
            out.showMessage(v.toString());
        }
    }

    public void findByTanque() throws IOException {
        TanqueService ts = new TanqueService();
        out.showMessage("BUSCAR POR TANQUE");
        out.showMessage("ID do tanque: ");
        Tanque t = ts.findById(in.readInteger()
                .orElseThrow(()-> new NullInputException("Id do Tanque"))
        );
        out.showMessage("Tanque selecionado: ");
        out.showMessage(t.toString());
        out.printLine();
        out.showMessage("Lista de Vendas: ");
        for(Venda v : vs.findByTanque(t)){
            out.showMessage(v.toString());
        }
    }

    public void findByData() throws IOException {
        out.showMessage("BUSCAR POR DATA");
        out.showMessage("Digite a data: ");
        String data = in.readDate().orElseThrow(()-> new NullInputException("Data"));
        out.showMessage("Lista de vendas: ");
        for(Venda v : vs.findByData(data)){
            out.showMessage(v.toString());
        }
    }



}
