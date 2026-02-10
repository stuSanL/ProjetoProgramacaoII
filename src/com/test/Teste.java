package com.test;

import com.controller.VendaController;
import com.exception.DataException;
import com.exception.NullInputException;
import com.ui.InputReader;
import com.ui.MenuRenderer;

import java.io.IOException;

public class Teste {


    public static void main(String[] args) throws IOException {
        InputReader in = new InputReader(System.in);
        MenuRenderer out = new MenuRenderer(System.out, in);
        VendaController v = new VendaController(in, out);
        try{
            v.findByData();
        }  catch(Exception e){
            out.showError(e.getMessage());
        }
    }
}
