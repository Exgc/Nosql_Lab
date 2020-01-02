package com.example.demo.controller;

import com.example.demo.Service.SecretService;
import com.example.demo.entity.relation.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @Autowired
    private SecretService secretService;

    @RequestMapping("/checkUser")
    public boolean checkUser(String sid,String password){
        try {
            if(secretService.getUser(sid,password)==null){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping("/updateSecret")
    public String updateSecrect(String sid,String password){
        try{
            secretService.updateUser(sid,password);
            return "succeed";
        }catch (Exception e){
            return e.toString();
        }
    }

    @RequestMapping("/insertSecret")
    public String insertSecrect(String sid,String password){
        try{
            Secret secret=new Secret();
            secret.setPassword(password);
            secret.setSid(sid);
            secretService.insertUser(secret);
            return "succeed";
        }catch (Exception e){
            return e.toString();
        }
    }
}
