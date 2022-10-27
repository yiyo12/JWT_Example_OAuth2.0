package com.example.JwtExmaple.utils;

import com.google.gson.Gson;

public class GsonUtils {

    //clase que usaremos para la serealizacion

    public static String serialize(Object src){
        Gson gson = new Gson();
        //lo que nos pàsen por parametro lo regresaremos con formato string json
        return gson.toJson(src);

    }
    // metodo que nos convierte el string json a un objeto o clase o identidad (o dto) que necesitemos dentro del a app
    public static <D> D toObject(String json, Class<D>dClass){
        Gson gson = new Gson();
        //recibe un return y obitne euna clase y hace el automapeo y lo retorna.
        //convertimos de un json a una clase
        return gson.fromJson(json, dClass);
    }

    // metodo que nos convierte el string json a un objeto o clase o identidad (o dto) que necesitemos dentro del a app
    public static <D> D toObject(Object src, Class<D>dClass){
        Gson gson = new Gson();
        //convertimos el objeto
        String src_json = gson.toJson(src,dClass);
        //recibe un return y obitne euna clase y hace el automapeo y lo retorna.
        //convertimos de un json a una clase
        return gson.fromJson(src_json, dClass);
    }



}
