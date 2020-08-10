package com.nostalgia.controller;

import com.nostalgia.entity.User;
import com.nostalgia.service.UserService;
import com.nostalgia.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class MainController {
    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public Map<String,Object> login(User user){
        Map<String,Object> resultMap=new HashMap<>();
        try {
            User userDB = userService.login(user);
            Map<String,Object> map=new HashMap<>();
            map.put("Id",userDB.getId());
            map.put("username",userDB.getUsername());
            String token = JWTUtils.getToken(map);
            resultMap.put("state",true);
            resultMap.put("msg","login succeed!");
            resultMap.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("state",false);
            resultMap.put("msg","login fail!");
        }
        return resultMap;
    }

    @GetMapping("/home")
    public String index(){
        return "welcome to home Page!";
    }

}
