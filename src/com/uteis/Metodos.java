package com.uteis;

import java.io.*;

public class Metodos {

    public long gerarId(String path) {
        long id = -1;
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            for(String l : br.lines().toList()){
                id++;
            }
        } catch (FileNotFoundException e) {
            id = 0;
        }
        return id;
    }
}
