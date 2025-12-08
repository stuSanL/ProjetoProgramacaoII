package com.exception;

public class ResourceInUseException extends RuntimeException {
    public ResourceInUseException(String recurso, String dependente) {
        super(recurso + " não pode ser excluído pois está sendo utilizado por " + dependente);
    }
}
