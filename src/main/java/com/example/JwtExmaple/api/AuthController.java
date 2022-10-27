package com.example.JwtExmaple.api;

import com.example.JwtExmaple.exceptions.Apiunauthorized;
import com.example.JwtExmaple.service.AuthService;
import com.example.JwtExmaple.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1.0")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthValidator validator;

    @RequestMapping(path = "oauth/client_credential/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody MultiValueMap<String,String> paramMap, @RequestParam("grant_type")String grantType) throws Apiunauthorized {
        validator.validate(paramMap,grantType);
        return ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
    }
}
