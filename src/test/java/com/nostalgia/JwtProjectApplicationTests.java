package com.nostalgia;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

//@SpringBootTest
class JwtProjectApplicationTests {

    @Test
    void contextLoads() {
        String privateKey="@123xyz.!";
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,3600);
        String token= JWT.create()
                .withClaim("username","何瑞")
                .withClaim("role","user")
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(privateKey));
        System.out.println(token);
        Date date=new Date();
        date.setTime(date.getTime()+3600000);
        System.out.println(date);
    }

    @Test
    void parseJWT(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoidXNlciIsImV4cCI6MTU5NzA0MzY0NywidXNlcm5hbWUiOiLkvZXnkZ4ifQ.nljGePvb-9q9c_6LerH_an9v1CnMI_z-t9wv5AQfMAM";
        String privateKey="@123xyz.!";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(privateKey)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println("用户名是:"+decodedJWT.getClaim("username").asString());
        System.out.println("过期时间是:"+decodedJWT.getExpiresAt());
    }



}
