package com.nostalgia.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JWTUtils {
    private static String privateKey="@123xyx.!";
    public static String getToken(Map<String,Object> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->builder.withClaim(k, String.valueOf(v)));
        Date date=new Date();
        date.setTime(date.getTime()+3600000);
        builder.withExpiresAt(date);
        return builder.sign(Algorithm.HMAC256(privateKey)).toString();
    }

    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(privateKey)).build().verify(token);
    }

    public static DecodedJWT parseToken(String token){
        return JWT.require(Algorithm.HMAC256(privateKey)).build().verify(token);
    }
}
