package com;

import com.model.Combustivel;
import com.model.Tanque;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        Combustivel c = new Combustivel("Gasolina", 4.99, 6.1);
//        Tanque tanque = new Tanque(c, 30);
//        //tanque.cadastrar();
//        c.nome = "Diesel";
//        tanque.atualizarCombustivel(01, c);
//
        BufferedReader br = new BufferedReader(new FileReader("src/com/data/tanques.csv"));
        br.lines().forEach(System.out::println);
    }
}
