package com.ui;

import com.exception.DataException;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public Optional<String> readDate(){
        Optional<String> l =  readLine();
        if(l.isEmpty()) return Optional.empty();
        try{
            LocalDate.parse(l.get().trim(), DateTimeFormatter.ISO_DATE);
            return Optional.of(l.get().trim());
        } catch(DateTimeParseException e){
            throw new DataException("Data invalida!");
        }
    }

}
