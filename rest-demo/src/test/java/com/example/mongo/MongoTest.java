package com.example.mongo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;

import com.example.Application;
import com.example.dao.EmpDao;
import com.example.pojo.Emp;

/**
 * @author bloodkilory
 *         generate on 15/6/8
 */
@ContextConfiguration(classes = {Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class MongoTest {

    @Resource
    private EmpDao empDao;

    @Test
    public void testGet() {
        List<Emp> emps = empDao.findByName("张三");
        TestCase.assertEquals(emps.size(), 1);
    }

}