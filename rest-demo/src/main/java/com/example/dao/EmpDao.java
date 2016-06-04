package com.example.dao;

import java.util.List;

import com.example.pojo.Emp;

/**
 * @author yangkun
 *         generate on 16/6/4
 */
public interface EmpDao {

    List<Emp> findByName(String name);
}
