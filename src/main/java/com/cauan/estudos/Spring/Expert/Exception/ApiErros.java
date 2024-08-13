package com.cauan.estudos.Spring.Expert.Exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(String mensagemErro){
       this.erros = Arrays.asList(mensagemErro);
    }
}
