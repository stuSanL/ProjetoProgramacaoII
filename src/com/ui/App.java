package com.ui;

import com.Paths;
import com.controller.*;

import java.io.File;
import java.io.IOException;

public class App {

    static private MenuRenderer out;
    static private CombustivelController coc;
    static private TanqueController tc;
    static private ClienteController clc;
    static private FuncionarioController fc;
    static private VendaController vc;

    static private final String[] opcoesBase =  {
            "Voltar", "Adicionar", "Excluir", "Listar",
            "Buscar por ID", "Atualizar", "Busca Especial"
    };

    static private final String[] opcoesMain = {
            "Sair", "Gerir Combustíveis",
            "Gerir Tanques", "Gerir Clientes",
            "Gerir Funcionários", "Gerir Vendas",
            "Inicializar/Reinicializar"
    };

    public App() {
        InputReader in = new InputReader(System.in);
        out = new MenuRenderer(System.out, in);
        coc = new CombustivelController(in, out);
        tc = new TanqueController(in, out);
        clc = new ClienteController(in, out);
        fc = new FuncionarioController(in, out);
        vc = new VendaController(in, out);
    }

    public void run() {
            menuPrincipal();
    }

    private static void menuPrincipal() {
        while(true){
            out.renderMenu("GPC", opcoesMain);
            switch(out.readOption(0, opcoesMain.length-1)){
                case 0: return;
                case 1: gerirCombustivel(); break;
                case 2: gerirTanque(); break;
                case 3: gerirCliente(); break;
                case 4: gerirFuncionario(); break;
                case 5: gerirVenda(); break;
                case 6:
                    initialize();
                    out.showMessage("Sistema reinicializado.");
                    break;
            }
            out.printLine();
        }
    }

    private static void gerirCombustivel() {
        while(true){
            out.renderMenu("Combustíveis", opcoesBase);
            try {
                switch(out.readOption(0, opcoesBase.length-1)){
                    case 0: return;
                    case 1: coc.add(); break;
                    case 2: coc.delete(); break;
                    case 3: coc.listAll(); break;
                    case 4: coc.findById(); break;
                    case 5: coc.update(); break;
                    case 6: buscaEspecialCombustivel(); break;
                }
            } catch (Exception e) {
                out.showError(e.getMessage());
            }

        }
    }

    private static void buscaEspecialCombustivel() throws IOException {
        coc.findByName();
    }

    private static void gerirTanque() {
        while(true){
            out.renderMenu("Tanques", opcoesBase);
            try {
                switch(out.readOption(0, opcoesBase.length -1)){
                    case 0: return;
                    case 1:
                        tc.add();
                        break;
                    case 2:
                        tc.delete();
                        break;
                    case 3:
                        tc.listAll();
                        break;
                    case 4:
                        tc.findById();
                        break;
                    case 5:
                        tc.update();
                        break;
                    case 6:
                        buscaEspecialTanque();
                        break;
                }
            } catch (Exception e) {
                out.showError(e.getMessage());
            }

        }
    }

    private static void buscaEspecialTanque() throws IOException {
        tc.findByCombustivel();
    }

    private static void gerirCliente() {
        while(true){
            out.renderMenu("Clientes", opcoesBase);
            try {
                switch(out.readOption(0,opcoesBase.length -1)){
                case 0: return;
                case 1:
                    clc.add();
                    break;
                case 2:
                    clc.delete();
                    break;
                case 3:
                    clc.listAll();
                    break;
                case 4:
                    clc.findById();
                    break;
                case 5:
                    clc.update();
                    break;
                case 6:
                    buscaEspecialCliente();
                    break;
                }
            } catch (Exception e) {
                out.showError(e.getMessage());
            }

        }
    }

    private static void buscaEspecialCliente() throws IOException {
        String[] buscasEspeciais = {
                "Voltar", "Nome", "Data"
        };
        out.renderMenu("Cliente: Busca especial", buscasEspeciais);
        switch (out.readOption(0, buscasEspeciais.length -1)) {
            case 0: return;
            case 1:
                clc.findByName();
                break;
            case 2:
                clc.findByData();
                break;

        }
    }

    private static void gerirFuncionario() {
        while(true){
            out.renderMenu("Funcionários", opcoesBase);
            try {
                switch(out.readOption(0, opcoesBase.length -1)){
                    case 0: return;
                    case 1:
                        fc.add();
                        break;
                    case 2:
                        fc.delete();
                        break;
                    case 3:
                        fc.listAll();
                        break;
                    case 4:
                        fc.findById();
                        break;
                    case 5:
                        fc.update();
                        break;
                    case 6:
                        buscaEspecialFuncionario();
                        break;
                }
            } catch (Exception e) {
                out.showError(e.getMessage());
            }

        }
    }

    private static void buscaEspecialFuncionario() throws IOException {
        String[] buscasEspeciais = {
                "Voltar", "Nome", "Data"
        };

        out.renderMenu("Funcionario: Busca especial", buscasEspeciais);

        switch (out.readOption(0, buscasEspeciais.length -1)) {
            case 0: return;
            case 1: fc.findByName(); break;
            case 2: fc.findByDate(); break;
        }
    }

    private static void gerirVenda() {
        while(true){
            out.renderMenu("Vendas", opcoesBase);
            try {
                switch(out.readOption(0, opcoesBase.length -1)){
                    case 0: return;
                    case 1:
                        vc.add();
                        break;
                    case 2:
                        vc.delete();
                        break;
                    case 3:
                        vc.listAll();
                        break;
                    case 4:
                        vc.findById();
                        break;
                    case 5:
                        vc.update();
                        break;
                    case 6:
                        buscaEspecialVenda();
                        break;
                }
            } catch (Exception e) {
                out.showError(e.getMessage());
            }

        }
    }

    private static void buscaEspecialVenda() throws IOException {
        String[] buscasEspeciais = {
                "Voltar", "Cliente", "Funcionario",
                "Tanque", "Data"
        };

        out.renderMenu("Cliente: Busca especial", buscasEspeciais);

        switch (out.readOption(0, buscasEspeciais.length -1)) {
            case 0: return;
            case 1: vc.findByCliente(); break;
            case 2: vc.findByFuncionario(); break;
            case 3: vc.findByTanque(); break;
            case 4: vc.findByData(); break;
        }
    }

    private static void initialize(){
        File f = new File(Paths.RAIZ.getPath());
        if(!f.exists()){
            f.mkdir();
        }
        String[] files = {
                Paths.COMBUSTIVEIS.getAbsPath(),
                Paths.TANQUES.getAbsPath(),
                Paths.CLIENTES.getAbsPath(),
                Paths.FUNCIONARIOS.getAbsPath(),
                Paths.VENDAS.getAbsPath(),
                Paths.IDS.getAbsPath()
        };

        for(String s : files){
            f = new File(s);
            if(!f.exists()) f.mkdir();
            else deleteFiles(f);
        }
    }

    private static void deleteFiles(File dir){
        if(dir.isDirectory()){
            for(File file : dir.listFiles()){
                if(file.isFile()){
                    file.delete();
                } else if(file.isDirectory()){
                    deleteFiles(file);
                }
            }
        } else dir.delete();
    }



}
