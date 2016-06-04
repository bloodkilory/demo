package com.example.pojo;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * mongodb demo pojo
 *
 * @author yangkun
 *         generate on 16/6/4
 */
@Document(collection = "emps")
public class Emp {
    @Id
    private String id;
    private String name;
    private String job;
    private String sex;
    private BigDecimal salary;
    private Integer age;

    public Emp() {
    }

    public Emp(String id, String name, String job, String sex, BigDecimal salary, Integer age) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.sex = sex;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Emp{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", job='").append(job).append('\'');
        sb.append(", sex='").append(sex).append('\'');
        sb.append(", salary=").append(salary);
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
