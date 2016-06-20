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

    private Emp(Builder builder) {
        id = builder.id;
        name = builder.name;
        job = builder.job;
        sex = builder.sex;
        salary = builder.salary;
        age = builder.age;
    }

    /**
     *
     */
    @Deprecated
    private static class SingletonBuilder {
        static Builder builder = new Builder();
    }

    /**
     * 取得单例化构建器****不可取,会产生线程安全性问题
     *
     * @return
     */
    @Deprecated
    public static Builder builder() {
        return SingletonBuilder.builder;
    }

    /**
     * 构建器工厂
     *
     * @param id
     * @param name
     * @return
     */
    public static Builder builder(String id, String name) {
        return new Builder(id, name);
    }

    /**
     * Emp构建器
     */
    public static class Builder implements com.example.common.Builder {
        // Required params
        private String id = null;
        private String name = null;

        // Optional params
        private String job = null;
        private String sex = null;
        private BigDecimal salary = BigDecimal.ZERO;
        private Integer age = 0;

        Builder() {
        }

        Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder job(String job) {
            this.job = job;
            return this;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Emp build() {
            return new Emp(this);
        }
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
