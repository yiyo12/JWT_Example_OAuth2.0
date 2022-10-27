package com.example.JwtExmaple.service;

import com.example.JwtExmaple.dto.JwtReponse;
import com.example.JwtExmaple.dto.UsuarioDto;
import com.example.JwtExmaple.security.JwtIO;
import com.example.JwtExmaple.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private JwtIO jwtio;

    @Autowired
    private DateUtils dateutils;

    @Value("${jms.jwt.token.expires-in}")
    private int EXPIRESS_IN;// ya la usa la clase JWTIO


    public JwtReponse login(String clientId, String clientSecret){

        UUID uid = UUID.randomUUID();

        UsuarioDto user = UsuarioDto.builder()
                .name("juan")
                .lastname("solis")
                .role("ADMIN")
                .uid(uid.toString())
                .build();

        //
        // aqui se arma la logica de autentificacion por eso necesitamos esos 2 parametros
        JwtReponse jwt = JwtReponse
                .builder()
                .tokenType("bearer")
                //em accessToken vamos pasarle un objeto para que lo serialize y lo meta al payload y poder manejarlo
                //en un front quizas. (informacion no tan sensible yq ue sean de utiilidad)
                //Ademas en este podemos cambiarlo por otras cosas como un id de la parsona, quizas un ROL
                .accessToken(jwtio.generateToken(user))
                .IssuedAdd(dateutils.getDateInMillis()+ "")
                .clientId(clientId)
                .expiresIn(jwtio.EXPIRES_IN) // esta variable ya la usa la clase JWTIO volvera a poner aqui es repetitivo
                .build(); // con esto tenemos contruido nuestro objeto de tipo jwt

        return jwt;
    }
}
