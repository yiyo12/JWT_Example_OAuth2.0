package com.example.JwtExmaple.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsuarioDto implements Serializable {

    private String uid;
    private String name;
    private String lastname;
    private String role;
    private String country;

}
