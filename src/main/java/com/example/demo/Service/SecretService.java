package com.example.demo.Service;

import com.example.demo.dao.SecretDao;
import com.example.demo.entity.relation.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretService {

    @Autowired
    private SecretDao secretDao;


    public Secret getUser(String sid, String password) {
        return secretDao.getUser(sid,password);
    }

    public void updateUser(String sid,String password){
        secretDao.updateUser(sid,password);
    }

    public void insertUser(Secret secret){
        secretDao.insertUser(secret);
    }
}
