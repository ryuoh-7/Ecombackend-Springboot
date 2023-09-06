package com.project225160694002.Ecomcombo.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project225160694002.Ecomcombo.model.LocalUser;
import com.project225160694002.Ecomcombo.model.dao.LocalUserDAO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;
    private static final String USERNAME_KEY="USERNAME";
    private final LocalUserDAO localUserDAO;

    public JWTService(LocalUserDAO localUserDAO) {
        this.localUserDAO = localUserDAO;
    }


    @PostConstruct
    public void postConstruct(){
        algorithm= Algorithm.HMAC256(algorithmKey);
    }

    public String generateJWT(LocalUser user){
        return JWT.create()
                .withClaim("USERNAME",user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+(1000*expiryInSeconds)))
                .withIssuer(issuer)
                .sign(algorithm);
    }



}
