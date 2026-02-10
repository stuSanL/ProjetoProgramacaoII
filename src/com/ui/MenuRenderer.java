package com.ui;

import java.io.PrintStream;
import java.util.Optional;

public class MenuRenderer {

    private final PrintStream out;
    private final InputReader in;

    public MenuRenderer(PrintStream out, InputReader in) {
        this.out = out;
        this.in = in;
    }

    public void printLine(){
     out.println("-----------------------");
    }

    public void renderMenu(String title, String[] opcoes){
        out.println("----- " + title + " -----");
        for(int i = 0; i < opcoes.length; i++){
            out.printf("%d - %s%n", i , opcoes[i]);
        }
        printLine();
    }

    public void showMessage(String message) {
        out.println(message);
    }

    public void showError(String message) {
        out.println("ERRO: " + message);
    }

    public void showUpdateMessage(){
        showMessage("Para qualquer campo abaixo, deixe a linha em branco caso não queira alterar.");
    }

    public int readOption(int min, int max){
        while(true){
            out.print("Escolha uma opção: ");
            Optional<Integer> opcao = in.readInteger();
            if(opcao.isEmpty()){
                showError("Opção inválida. Insira um número.");
                continue;
            }
            if(opcao.get() >= min && opcao.get() <= max){
                return opcao.get();
            } else {
                showError("Opção inválida. Insira no intervalo entre " + min + " e " + max + ".");
            }

        }
    }
}
