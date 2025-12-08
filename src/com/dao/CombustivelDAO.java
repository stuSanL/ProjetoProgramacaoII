package com.dao;

import com.Paths;
import com.exception.DataException;
import com.model.Combustivel;

import java.io.*;
import java.util.Objects;

public class CombustivelDAO {


    public void add(Combustivel c) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(Paths.COMBUSTIVEIS.getAbsPath() + "/" + c.getId()));
            pw.append(String.valueOf(c.getId())).append("\n")
                    .append(c.getNome()).append("\n")
                    .append(String.valueOf(c.getCusto())).append("\n")
                    .append(String.valueOf(c.getValor()));
            pw.close();
    }

    public Combustivel findByID(int id) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(Paths.COMBUSTIVEIS.getAbsPath() + "/" + id));
        Combustivel c = new Combustivel(
                Integer.parseInt(br.readLine()),
                br.readLine(),
                Double.parseDouble(br.readLine()),
                Double.parseDouble(br.readLine())
        );
        br.close();
        return c;
    }

    public void deleteByID(int id) throws IOException {
        File f = new File(Paths.COMBUSTIVEIS.getAbsPath() + "/" + id);
        if(!f.exists()) throw new FileNotFoundException();
        boolean d = f.delete();
        if (!d) throw new DataException("Erro ao deletar combustível");
    }

    public void update(Combustivel c) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.COMBUSTIVEIS.getAbsPath() + "/" + c.getId(), false));
            bw.append(String.valueOf(c.getId())).append("\n")
                    .append(c.getNome()).append("\n")
                    .append(String.valueOf(c.getCusto())).append("\n")
                    .append(String.valueOf(c.getValor()));
            bw.close();
    }

    public Combustivel[] listAll() throws IOException {
        File[] files = new File(Paths.COMBUSTIVEIS.getAbsPath()).listFiles();
        Combustivel[] combustiveis = new Combustivel[Objects.requireNonNull(files).length];
        int i = 0;

        for (File file : files) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            combustiveis[i++] = findByID(Integer.parseInt(br.readLine()));
            br.close();
        }
        verificaVetorVazio(combustiveis);
        return combustiveis;
    }

    public Combustivel[] findByNome(String nome) throws IOException {
        nome = nome.toLowerCase();
        int i = 0;
        for (Combustivel c : listAll()) {
            if (c.getNome().toLowerCase().contains(nome)) {
                i++;
            }
        }

        Combustivel[] encontrados = new Combustivel[i];
        i = 0;
        for (Combustivel c : listAll()) {
            if (c.getNome().toLowerCase().contains(nome)) {
                encontrados[i++] = c;
            }
        }

        verificaVetorVazio(encontrados);
        return encontrados;
    }

    public void verificaVetorVazio (Combustivel[] v) throws DataException {
        if(v.length == 0) throw new DataException("Nenhum combustível encontrado.");
    }
}
