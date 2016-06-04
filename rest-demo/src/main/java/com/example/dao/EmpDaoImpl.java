package com.example.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.pojo.Emp;

/**
 * @author yangkun
 *         generate on 16/6/4
 */
@Repository
public class EmpDaoImpl implements EmpDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<Emp> findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Emp.class);
    }
}
