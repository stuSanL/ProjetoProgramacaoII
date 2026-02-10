package com.dao;

import com.Paths;
import com.exception.DataException;
import com.model.Funcionario;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FuncionarioDAO {

    public void add(Funcionario f) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(Paths.FUNCIONARIOS.getAbsPath()+"/"+f.getId()));
            pw.append(String.valueOf(f.getId())).append("\n")
                .append(f.getNome()).append("\n")
                .append(f.getDataNascimento().format(DateTimeFormatter.ISO_DATE)).append("\n")
                .append(f.getSexo()).append("\n")
                .append(f.getCpf()).append("\n")
                .append(f.getTelefone()).append("\n")
                .append(f.getMatricula()).append("\n")
                .append(f.getCargo()).append("\n")
                .append(String.valueOf(f.getSalario()));
            pw.flush();
            pw.close();
    }

    public Funcionario findByID(int id) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Paths.FUNCIONARIOS.getAbsPath() + "/" + id));
        Funcionario f = new Funcionario(
            Integer.parseInt(br.readLine()),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                br.readLine(),
                Double.parseDouble(br.readLine())
        );
        br.close();
        return f;
    }

    public void deleteByID(int id) {
        File f = new File(Paths.FUNCIONARIOS.getAbsPath() +"/" + id);
        if(!f.exists()) throw new DataException("Arquivo não encontrado");
        boolean d = f.delete();
        if (!d) throw new DataException("Erro ao deletar funcionário");
    }

    public void update(Funcionario f) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.FUNCIONARIOS.getAbsPath() + "/" + f.getId(), false));
        bw.append(String.valueOf(f.getId())).append("\n")
            .append(f.getNome()).append("\n")
            .append(f.getDataNascimento().format(DateTimeFormatter.ISO_DATE)).append("\n")
            .append(f.getSexo()).append("\n")
            .append(f.getCpf()).append("\n")
            .append(f.getTelefone()).append("\n")
            .append(f.getMatricula()).append("\n")
            .append(f.getCargo()).append("\n")
            .append(String.valueOf(f.getSalario()));
        bw.close();
    }

    public Funcionario[] listAll() throws IOException {
        File[] files =  new File(Paths.FUNCIONARIOS.getAbsPath()).listFiles();
        Funcionario[] funcionarios = new Funcionario[Objects.requireNonNull(files).length];
        int i = 0;
        for(File file : files){
            BufferedReader br = new BufferedReader(new FileReader(file));
            funcionarios[i++] = findByID(Integer.parseInt(br.readLine()));
            br.close();
        }
        verificaVetorVazio(funcionarios);
        return funcionarios;
    }

    public Funcionario[] findByNome(String nome) throws IOException {
        int i = 0;
        for(Funcionario f : listAll()){
            if(f.getNome().toLowerCase().contains(nome.toLowerCase())) i++;
        }
        Funcionario[] funcionarios = new Funcionario[i];
        i = 0;
        for(Funcionario f : listAll()){
            if(f.getNome().toLowerCase().contains(nome.toLowerCase())){
                funcionarios[i] = f;
                i++;
            }
        }
        verificaVetorVazio(funcionarios);
        return funcionarios;
    }

    public Funcionario[] findByData(String data) throws IOException {
        LocalDate d = LocalDate.parse(data, DateTimeFormatter.ISO_DATE);

        int i = 0;

        for(Funcionario f : listAll()){
            if(f.getDataNascimento().isEqual(d)) i++;
        }

        Funcionario[] funcionarios = new Funcionario[i];
        i = 0;

        for(Funcionario f : listAll()){
            if(f.getDataNascimento().isEqual(d)) funcionarios[i++] = f;
        }

        verificaVetorVazio(funcionarios);
        return funcionarios;
    }

    public void verificaVetorVazio (Funcionario[] v){
        if(v.length == 0) throw new DataException("Nenhum funcionário encontrado.");
    }
}
