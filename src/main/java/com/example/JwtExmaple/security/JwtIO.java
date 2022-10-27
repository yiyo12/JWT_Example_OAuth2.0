package com.example.JwtExmaple.security;

import com.example.JwtExmaple.utils.GsonUtils;
import io.fusionauth.jwt.JWTUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.fusionauth.jwt.domain.JWT;

import java.time.ZonedDateTime;
import java.util.TimeZone;


@Component //para inyectarlo luego en otra clase
public class JwtIO {
    @Value("${jms.jwt.token.secret:secret}") //al poner : agregamos una propiedad por defeecto
    private String SECRET; // palanra secreta para firmar nuestro jwt
    @Value("${jms.jwt.timezone:UTC}")
    private String TIMEZONE;
    @Value("${jms.jwt.token.expires-in}")
    public int EXPIRES_IN;
    @Value("${jms.jwt.issuer}")
    private String ISSUER;

    public String generateToken(Object src){
        String subject = GsonUtils.serialize(src);
        //construir la firma: HMAC signer SHA-256
        Signer signer = HMACSigner.newSHA256Signer(SECRET);

        TimeZone tmz = TimeZone.getTimeZone(TIMEZONE);
        // con eso al tiempo actual le agregamos el tiempo de expiracion
        ZonedDateTime zoneDT = ZonedDateTime.now(tmz.toZoneId()).plusSeconds(EXPIRES_IN);

        JWT jwt = new JWT()
                .setIssuer(ISSUER)
                .setIssuedAt(ZonedDateTime.now(tmz.toZoneId()))
                .setSubject(subject)
                .setExpiration(zoneDT);
        //esto genera el string del jwt
        return JWT.getEncoder().encode(jwt,signer);

    }
    //recibe el jwt codificado
    public boolean validateToken(String encodedJWT){


        boolean result = false;

        try {
            //es el mismo metodo que estamos usando aqui en la clase
            JWT jwt = jwt(encodedJWT);
            //validar si ya a expirado. Esto indica si ya expiro ya que isExpired devuelve tru o false. True es que ya expiro
            result = jwt.isExpired();
        }catch (Exception e){

            result = true;

        }



        return result;
    }

    //metodo para obtener el payload
    public String getPayLoad(String encodedJWT){
        //es el mismo metodo que estamos usando aqui en la clase
        JWT jwt = jwt(encodedJWT);
        //subjet contiene lo que serealizamos
         return jwt.subject;
    }

   private JWT jwt(String encodedJWT){
       //Verifier verifier = HMACVerifier.newVerifier(SECRET);
       JWT jwt = JWTUtils.decodePayload(encodedJWT);
    //Esto regresa ya el jwt como lo necesitamos. Osea nos crea el string token. (lo mismo que vemos en la pagina web de jwt)
       //return JWT.getDecoder().decode(encodedJWT,verifier);
       return jwt;
   }

}
