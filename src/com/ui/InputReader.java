package com.ui;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class InputReader {

    private final Scanner scanner;

    public InputReader(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public Optional<String> readLine() {
        String line = scanner.nextLine();
        if(line.isEmpty()) return Optional.empty();
        return Optional.of(line);
    }

    public Optional<Integer> readInteger() {
        Optional<String> l = readLine();
        if(l.isEmpty()) return Optional.empty();
        try{
            return Optional.of(Integer.parseInt(l.get().trim()));
        } catch(NumberFormatException e){
            return Optional.empty();
        }
    }

    public Optional<Double> readDouble() {
        Optional<String> l = readLine();
        if(l.isEmpty()) return Optional.empty();
        try{
            return Optional.of(Double.parseDouble(l.get().trim()));
        } catch(NumberFormatException e){
            return Optional.empty();
        }
    }

    public Optional<Boolean> readBoolean() {
        Optional<String> l =  readLine();
        return l.flatMap(s -> switch (s.trim().toLowerCase()) {
            case "s", "sim", "y", "yes", "true", "1" -> Optional.of(true);
            case "n", "nao", "não", "no", "false", "0" -> Optional.of(false);
            default -> Optional.empty();
        });
    }

}
