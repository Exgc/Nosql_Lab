package com.example.demo.dao;

import com.example.demo.entity.relation.Secret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class SecretDao {

    @Autowired
    MongoTemplate mongoTemplate;


    public Secret getUser(String sid, String password) {
        Query query=new Query(Criteria.where("sid").is(sid).and("password").is(password));
        return mongoTemplate.findOne(query,Secret.class,"secret");
    }

    public void updateUser(String sid,String password){
        Query query=new Query();
        query.addCriteria(Criteria.where("sid").is(sid));
        Update update=new Update();
        update.set("password",password);
        update.set("sid",sid);
        mongoTemplate.updateFirst(query,update,"secret");
    }

    public void insertUser(Secret secret){
        mongoTemplate.save(secret,"secret");
    }
}
