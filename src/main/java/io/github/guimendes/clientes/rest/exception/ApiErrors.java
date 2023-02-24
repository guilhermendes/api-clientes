package io.github.guimendes.clientes.rest.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors){
        this.errors = errors;
    }

    public ApiErrors(String message){
        this.errors = Collections.singletonList(message);
    }

}
