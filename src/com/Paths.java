package com;

public enum Paths {

    RAIZ("data"),
    FUNCIONARIOS( "/funcionarios"),
    CLIENTES("/clientes"),
    COMBUSTIVEIS("/combustiveis"),
    TANQUES("/tanques"),
    VENDAS("/vendas"),
    IDS("/ids");

    private final String path;

    Paths(String path){
        this.path = path;
    }

    public String getAbsPath(){
        return RAIZ.getPath() + path;
    }

    public String getPath(){
        return path;
    }

}
