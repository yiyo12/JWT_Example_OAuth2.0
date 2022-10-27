package com.example.JwtExmaple.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class JwtReponse {

    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "exìres_in")
    private int expiresIn;
    @JsonProperty(value = "issued_at")
    private String IssuedAdd; //En que momento fue solicitado el token
    @JsonProperty(value = "client_id")
    private String clientId;
}
