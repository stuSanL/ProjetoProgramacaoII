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

    public void run() throws IOException {
        int opcao;
        while(true){
            out.renderMenu("GPC", opcoesMain);
            opcao = out.readOption(0, 7);
            switch(opcao){
                case 0: return;
                case 1:
                    gerirCombustivel();
                    break;
                case 2:
                    gerirTanque();
                    break;
                case 3:
                    gerirCliente();
                case 4:
                    gerirFuncionario();
                    break;
                case 5:
                    gerirVenda();
                    break;
                case 6:
                    initialize();
                    out.showMessage("Sistema reinicializado.");
                    break;
            }
            out.printLine();
        }
    }

    private static void gerirCombustivel() throws IOException {
        int opcao;
        while(true){
            out.renderMenu("Combustíveis", opcoesBase);
            opcao = out.readOption(0, 7);
            switch(opcao){
                case 0: return;
                case 1:
                    coc.add();
                    break;
                case 2:
                    coc.delete();
                    break;
                case 3:
                    coc.listAll();
                    break;
                case 4:
                    coc.findById();
                    break;
                case 5:
                    coc.update();
                    break;
                case 6:
                    System.out.println("Busca especial:");
                    break;
            }
        }
    }

    private static void gerirTanque() throws IOException {
        int opcao;
        while(true){
            out.renderMenu("Tanques", opcoesBase);
            opcao = out.readOption(0, 7);
            switch(opcao){
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
                    System.out.println("Busca especial:");
                    break;
            }
        }
    }

    private static void gerirCliente() throws IOException {
        int opcao;
        while(true){
            out.renderMenu("Clientes", opcoesBase);
            opcao = out.readOption(0, 7);
            switch(opcao){
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
                    System.out.println("Busca especial:");
                    break;
            }
        }
    }

    private static void gerirFuncionario() throws IOException {
        int opcao;
        while(true){
            out.renderMenu("Funcionários", opcoesBase);
            opcao = out.readOption(0, 7);
            switch(opcao){
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
                    System.out.println("Busca especial:");
                    break;
            }
        }
    }

    private static void gerirVenda() throws IOException {
        int opcao;
        while(true){
            out.renderMenu("Vendas", opcoesBase);
            opcao = out.readOption(0, 7);
            switch(opcao){
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
                    System.out.println("Busca especial:");
                    break;
            }
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
