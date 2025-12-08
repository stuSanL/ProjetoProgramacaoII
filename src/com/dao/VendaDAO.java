package com.dao;

import com.Paths;
import com.exception.DataException;
import com.model.Cliente;
import com.model.Funcionario;
import com.model.Tanque;
import com.model.Venda;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class VendaDAO {

    public void add(Venda v) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(Paths.VENDAS.getAbsPath() + "/" + v.getId()));
        pw.append(String.valueOf(v.getId())).append("\n")
            .append(String.valueOf(v.getCliente().getId())).append("\n")
            .append(String.valueOf(v.getFuncionario().getId())).append("\n")
            .append(String.valueOf(v.getTanque().getId())).append("\n")
            .append(String.valueOf(v.getLitragem())).append("\n")
            .append(String.valueOf(v.getValor())).append("\n")
            .append(String.valueOf(v.getDataVenda())).append("\n");
        pw.close();
    }

    public Venda findById(int id) throws IOException {
        ClienteDAO cDAO = new ClienteDAO();
        FuncionarioDAO fDAO = new FuncionarioDAO();
        TanqueDAO tDAO = new TanqueDAO();

        BufferedReader br = new BufferedReader(new FileReader(Paths.VENDAS.getAbsPath() + "/" + id));
        Venda v = new Venda(
                Integer.parseInt(br.readLine()),
                cDAO.findByID(Integer.parseInt(br.readLine())),
                fDAO.findByID(Integer.parseInt(br.readLine())),
                tDAO.findByID(Integer.parseInt(br.readLine())),
                Double.parseDouble(br.readLine()),
                Double.parseDouble(br.readLine()),
                br.readLine()
        );
        br.close();
        return v;
    }

    public void deleteById(int id){
        File f =  new File(Paths.VENDAS.getAbsPath() + "/" + id);
        if(!f.exists()) throw new DataException("Arquivo não encontrado");
        boolean d = f.delete();
        if (!d) throw new DataException("Erro ao deletar venda");
    }

    public void update(Venda v) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.VENDAS.getAbsPath() + "/" + v.getId(), false));
        bw.append(String.valueOf(v.getId())).append("\n")
            .append(String.valueOf(v.getCliente().getId())).append("\n")
            .append(String.valueOf(v.getFuncionario().getId())).append("\n")
            .append(String.valueOf(v.getTanque().getId())).append("\n")
            .append(String.valueOf(v.getLitragem())).append("\n")
            .append(String.valueOf(v.getValor()))
            .append(String.valueOf(v.getDataVenda()));
        bw.close();
    }

    public Venda[] listAll() throws IOException {
        File[] files = new File(Paths.VENDAS.getAbsPath()).listFiles();
        Venda[] vendas = new Venda[Objects.requireNonNull(files).length];

        int i = 0;
        for(File file : files){
            BufferedReader br = new BufferedReader(new FileReader(file));
                vendas[i++] = findById(Integer.parseInt(br.readLine()));
                br.close();
        }
        verificaVetorVazio(vendas);
        return vendas;
    }

    public Venda[] findByCliente(Cliente c) throws IOException {
        ClienteDAO cDAO = new ClienteDAO();
        Venda[] vendas;

        int i = 0;
        for(Venda v : listAll()){
            if(v.getCliente().getId() == c.getId()) i++;
        }
        vendas = new Venda[i];
        i = 0;
        for(Venda v :  listAll()){
            if(v.getCliente().getId() == c.getId()) vendas[i++] = v;
        }
        verificaVetorVazio(vendas);
        return vendas;
    }

    public Venda[] findByFuncionario(Funcionario f) throws IOException {
        FuncionarioDAO fDAO = new FuncionarioDAO();
        Venda[] vendas;
        int i = 0;
        for(Venda v : listAll()){
            if(v.getFuncionario().getId() == f.getId()) i++;
        }
        vendas = new Venda[i];
        i = 0;
        for(Venda v :  listAll()){
            if(v.getFuncionario().getId() == f.getId()) vendas[i++] = v;
        }
        verificaVetorVazio(vendas);
        return vendas;
    }

    public Venda[] findByTanque(Tanque t) throws IOException {
        Venda[] vendas;
        int i = 0;
        for(Venda v : listAll()){
            if(v.getTanque().getId() == t.getId()) i++;
        }
        vendas = new Venda[i];
        i = 0;
        for(Venda v :  listAll()){
            if(v.getTanque().getId() == t.getId()) vendas[i++] = v;
        }
        verificaVetorVazio(vendas);
        return vendas;
    }

    public Venda[] findByData(String data) throws IOException {
        LocalDate d =  LocalDate.parse(data, DateTimeFormatter.ISO_DATE);
        Venda[] vendas;
        int i = 0;
        for(Venda v :  listAll()){
            if(v.getDataVenda().equals(d)) i++;
        }
        vendas = new Venda[i];
        i = 0;
        for(Venda v :  listAll()){
            if(v.getDataVenda().equals(d)) vendas[i++] = v;
        }
        verificaVetorVazio(vendas);
        return vendas;
    }

    public void verificaVetorVazio (Venda[] v){
        if(v.length == 0) throw new DataException("Nenhuma venda encontrada.");
    }
}
