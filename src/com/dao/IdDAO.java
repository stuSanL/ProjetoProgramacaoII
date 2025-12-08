package com.dao;

import com.exception.DataException;

import java.io.*;

public class IdDAO {

    public IdDAO() {
    }

    public int readID(String path){
        int id;
        String linha;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            linha = br.readLine();
            if(linha != null){
                id = Integer.parseInt(linha);
            } else {
                return 0;
            }
        }catch(IOException e){
            throw new DataException("Erro ao ler o arquivo de id (" +  path + "): " + e.getMessage());
        }
        return id;
    }

    public int recordId(String path){
        createFile(path);
        int id = readID(path);
        try(PrintWriter pw = new PrintWriter(new FileWriter(path))){
            pw.println(++id);
            return id;
        } catch (IOException e){
            throw new DataException("Erro ao gravar o arquivo de id (" +  path + "): " + e.getMessage());
        }
    }

    public void createFile(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DataException("O arquivo de ID não pôde ser criado: " + e.getMessage());
            }
        }
    }

}
