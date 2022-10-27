package com.example.JwtExmaple.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class InterceptorJwtIO implements HandlerInterceptor {

    //Rutas exluidas, path de autentificacion
    @Value("${jms.jwt.token.auth.path}")
    private String AUTH_PATH;
    //excluded:
    //path: "/v1.0/multi,/v1.0/otraexclusion"
    // de nuestro archivo de configuracion tenemos estas rutas exluidas
    //la sentencia de abajo tiene # que lo que hace
    @Value("#{'${jms.jwt.excluded.path}'.split(',')}")
    //@Value("${jms.jwt.excluded.path}")
    private List<String> rutasExcluidas;

    @Autowired
    private JwtIO jwtIO;

    //Este metodo se usa para capturar para atrpat todas esas peticiones que llegan a nuestro servidor
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean validate = false;

        String url = request.getRequestURI();//con esto obtenemros la URL de nuestra peticion , ahora verificamos si no esta exluida o si es nuestra ruta de autentificacion

        if (url.equals(AUTH_PATH) || excluded(url)){
            //Si esta exluida no tiene que valida el token
            validate = true;
        }

        //ahora tenemos que validar en caso de que el if de arriba no aplique
        //tenemos que validar nuestro authorization que no este vacio etc
        //si sigue siendo falto y autorizacion es diferente de null y el header no viene vacio

        if (!validate && request.getHeader("Authorization")!=null && !request.getHeader("Authorization").isEmpty()){

            //Con esto remplazamos el bearer y lo removemos para obtener solamente el token y poder validarlo con nuestra clase jwtIO el
            //cual ya tiene un metodo para esa validacion
            String token = request.getHeader("Authorization").replace("Bearer", "");
            //Si el token es valido devuelve un valor booleano (si el token ya expiro)
            //Esete metodo validate devuelve un true si ya expiro entonces nosotros negamos el true para devulver el false que necesitamos de este lado
            //porque significa que l token ya expiro
            validate = !jwtIO.validateToken(token);
        }

        if(!validate){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return validate;
    }

    //recibe el path y lo validaremos en nuestra lista de exclusiomnes
    private boolean excluded(String path){

        boolean result = false;

        //recorremos las rutas excluidas

        for (String exc: rutasExcluidas){
           // exc.split(",");
            //El gato nos sirve para saber si se tiene o no adtos por defecto
            if (!exc.equals("#") && exc.equals(path)){
                result = true;
            }
        }

        return result;


    }
}
