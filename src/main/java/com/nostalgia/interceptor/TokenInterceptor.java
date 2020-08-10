package com.nostalgia.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nostalgia.util.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        Map<String,Object> map= new HashMap<>();
        try{
            JWTUtils.verify(token);
            return true;
        }catch (TokenExpiredException e){
            map.put("msg","token is expired!");
        }catch (SignatureVerificationException e){
            map.put("msg","signature check fail!");
        }catch (AlgorithmMismatchException e){
            map.put("msg","crypto algorithm mismatch!");
        }catch (Exception e){
            map.put("msg","unknown exception");
        }
        map.put("state",false);
        String s = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(s);
        return false;
    }
}
