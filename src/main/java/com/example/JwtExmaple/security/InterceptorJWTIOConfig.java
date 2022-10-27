package com.example.JwtExmaple.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorJWTIOConfig implements WebMvcConfigurer {
    //recoerdar que :false seria un valor por defecto si en dado caso en el
    //archivo yml no pusieran dicha propiedad
    @Value("${jms.jwt.security.enable:false}")
    private boolean secutiryEnable;

    @Autowired
    private InterceptorJwtIO interceptiorJWTIO;

    //En esta parte tuve que forzosamente usar un constructor para inyectar de forma correct el interceptor en addInterceptioprs
    public InterceptorJWTIOConfig(InterceptorJwtIO interceptiorJWTIO) {
        this.interceptiorJWTIO = interceptiorJWTIO;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (secutiryEnable){
            registry.addInterceptor(this.interceptiorJWTIO);
        }
    }
}
