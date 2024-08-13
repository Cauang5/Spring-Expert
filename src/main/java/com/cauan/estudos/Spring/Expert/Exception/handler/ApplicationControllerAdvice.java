package com.cauan.estudos.Spring.Expert.Exception.handler;

import com.cauan.estudos.Spring.Expert.Exception.ApiErros;
import com.cauan.estudos.Spring.Expert.Exception.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException exception){
        String mensagemErro = exception.getMessage();
        return new ApiErros(mensagemErro);
    }
}
