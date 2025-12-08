package com.dao;

import com.Paths;
import com.exception.DataException;
import com.model.Cliente;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ClienteDAO {

    public void add(Cliente c) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(Paths.CLIENTES.getAbsPath()+"/"+ c.getId()));
            pw.append(String.valueOf(c.getId())).append("\n")
                    .append(c.getNome()).append("\n")
                    .append(c.getDataNascimento().format(DateTimeFormatter.ISO_DATE)).append("\n")
                    .append(c.getSexo()).append("\n")
                    .append(c.getCpf()).append("\n")
                    .append(c.getTelefone()).append("\n")
                    .append(String.valueOf(c.haveAssinatura()));
            pw.close();
    }

    public Cliente findByID(int id) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Paths.CLIENTES.getAbsPath() + "/" + id));
        Cliente c = new Cliente(
                Integer.parseInt(br.readLine()),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                Boolean.parseBoolean(br.readLine())
        );
        br.close();
        return c;
    }

    public void deleteByID(int id) throws IOException {
        File f = new File(Paths.CLIENTES.getAbsPath() + "/" + id);
        if(!f.exists()) throw new IOException("Arquivo não encontrado");
        boolean d = f.delete();
        if (!d) throw new DataException("Erro ao deletar");
    }

    public void update(Cliente c) throws IOException {
       BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.CLIENTES.getAbsPath() + "/" +c.getId(), false));
        bw.append(String.valueOf(c.getId())).append("\n")
        .append(c.getNome()).append("\n")
        .append(c.getDataNascimento().format(DateTimeFormatter.ISO_DATE)).append("\n")
        .append(c.getSexo()).append("\n")
        .append(c.getCpf()).append("\n")
        .append(c.getTelefone()).append("\n")
        .append(String.valueOf(c.haveAssinatura()));
       bw.close();
    }

    public Cliente[] listAll() throws IOException {
        File[] files =  new File(Paths.CLIENTES.getAbsPath()).listFiles();
        Cliente[] clientes = new Cliente[Objects.requireNonNull(files).length];
        int i = 0;
        for(File file : files){
            BufferedReader br = new BufferedReader(new FileReader(file));
            clientes[i++] = findByID(Integer.parseInt(br.readLine()));
            br.close();
        }
        verificaVetorVazio(clientes);
        return clientes;
    }

    public Cliente[] findByNome(String nome) throws IOException {
        int i = 0;
        for(Cliente c : listAll()){
            if(c.getNome().toLowerCase().contains(nome.toLowerCase())) i++;
        }
        Cliente[] clientes = new Cliente[i];

        if(clientes.length == 0) throw new DataException("Sem clientes com esse nome ( " + nome + " )");

        i = 0;
        for(Cliente c : listAll()){
            if(c.getNome().toLowerCase().contains(nome.toLowerCase())){
                clientes[i++] = c;
            }
        }
        verificaVetorVazio(clientes);
        return clientes;
    }

    public void verificaVetorVazio (Cliente[] v){
        if(v.length == 0) throw new DataException("Nenhum cliente encontrado.");
    }

}
