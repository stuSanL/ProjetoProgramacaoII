package com.dao;

import com.Paths;
import com.exception.DataException;
import com.model.Combustivel;
import com.model.Tanque;

import java.io.*;
import java.util.Objects;

public class TanqueDAO {

    public void add(Tanque t) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(Paths.TANQUES.getAbsPath() + "/" +t.getId()));
                pw.append(String.valueOf(t.getId())).append("\n")
                        .append(String.valueOf(t.getCombustivel().getId())).append("\n")
                        .append(String.valueOf(t.getCapacidade())).append("\n")
                        .append(String.valueOf(t.getVolumeAtual()));
                pw.flush();
        pw.close();
    }

    public Tanque findByID(int id) throws IOException {
        CombustivelDAO cDAO =  new CombustivelDAO();
        BufferedReader br = new BufferedReader(new FileReader(Paths.TANQUES.getAbsPath() + "/" + id));
        Tanque t = new Tanque(
                Integer.parseInt(br.readLine()),
                cDAO.findByID(Integer.parseInt(br.readLine())),
                Double.parseDouble(br.readLine()),
                Double.parseDouble(br.readLine())
        );
        br.close();
        return t;
    }

    public void deleteByID(int id){
        File f = new  File(Paths.TANQUES.getAbsPath() + "/" + id);
        if(!f.exists()) throw new DataException("Arquivo não encontrado.");
        boolean d = f.delete();
        if (!d) throw new DataException("Erro ao deletar tanque.");
    }

    public void update(Tanque t) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(Paths.TANQUES.getAbsPath() + "/" + t.getId(), false));
        bw.append(String.valueOf(t.getId())).append("\n")
            .append(String.valueOf(t.getCombustivel().getId())).append("\n")
            .append(String.valueOf(t.getCapacidade())).append("\n")
            .append(String.valueOf(t.getVolumeAtual()));
        bw.close();
    }

    public Tanque[] listAll() throws IOException {
        File[] files = new File(Paths.TANQUES.getAbsPath()).listFiles();
        Tanque[] tanques = new  Tanque[Objects.requireNonNull(files).length];
        int i = 0;
        for(File f:  files){
            BufferedReader br = new BufferedReader(new FileReader(f));
            tanques[i++] = findByID(Integer.parseInt(br.readLine()));
            br.close();
        }
        verificaVetorVazio(tanques);
        return tanques;
    }

    public Tanque[] findByCombustivel(Combustivel combustivel) throws IOException {
        Tanque[] tanques;
        int i = 0;
        for(Tanque t:  listAll()){
            if(t.getCombustivel().equals(combustivel)) i++;
        }
        tanques = new Tanque[i];
        i = 0;
        for(Tanque t:  listAll()){
            if(t.getCombustivel().equals(combustivel)){
                tanques[i++] = t;
            }
        }
        verificaVetorVazio(tanques);
        return tanques;
    }

    public void verificaVetorVazio (Tanque[] v){
        if(v.length == 0) throw new DataException("Nenhum tanque encontrado.");
    }
}
