package com.example.JwtExmaple.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jms.jwt")
public class JwtIOProperties {

    private Secutiry secutiry;
    private String timezone;
    private String issuer;
    private Token token;
    private Excluded excluded;

    @Data
    public static class Secutiry{
        //con esto controlamos si la seguridad esta activa o inactiva
        private boolean enabled;
    }

    @Data
    public static class Token{
        //
        private Auth auth;
        private String secret;
        private int expiresIn;
    }

    @Data
    public static class Auth{
        //
        private String path;
    }

    @Data
    public static class Excluded{
        //
        private boolean enabled;
    }

}
