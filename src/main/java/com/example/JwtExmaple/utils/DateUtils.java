package com.example.JwtExmaple.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component // para poderla auto inyectar en cualqueir parte dela app
public class DateUtils {

    @Value("${jms.jwt.timezone}") //esto hace referencia anuestro archivo de propediades
    private String TIMEZONE;

    private SimpleDateFormat simpleDateFormat(){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); // hay mas tipos de formatos
        sdf.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
    return sdf;
    }

    public String getDateString(){
        Date now = new Date();

        return simpleDateFormat().format(now);
    }

    public long getDateInMillis(){
        Date now = new Date();
        String strDate = simpleDateFormat().format(now);
        Date strNow = new Date();

        try {
            strNow = simpleDateFormat().parse(strDate);
        } catch (ParseException e) {

        }

        return strNow.getTime();

    }

}
