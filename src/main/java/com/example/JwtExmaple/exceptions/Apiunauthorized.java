package com.example.JwtExmaple.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Apiunauthorized extends Exception {

    //clase que respone cuando una autorizacio n no es valida

    public Apiunauthorized(String message){
        super(message);
    }


}
