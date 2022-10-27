package com.example.JwtExmaple.validator;

import com.example.JwtExmaple.exceptions.Apiunauthorized;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Objects;

//para que se pueda inyectar le agregamos component
@Component
public class AuthValidator {

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    public void validate(MultiValueMap<String,String> paramMap, String grantType) throws Apiunauthorized {

        if (grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)){
            message("El campo grantType es invalido");
        }

        if (Objects.isNull(paramMap) || paramMap.getFirst("client_id").isEmpty() || paramMap.getFirst("client_secret").isEmpty()){
            message("Campo client_id o client_secret no son validos");

        }

    }
    //se encarga de mostar el mensaje
    private void message(String message) throws Apiunauthorized {
        throw new Apiunauthorized(message);
    }

}
