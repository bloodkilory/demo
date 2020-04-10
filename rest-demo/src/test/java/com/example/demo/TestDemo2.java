package com.example.demo;

import org.springframework.beans.BeanUtils;

/**
 * @author yangkun
 * generate on 2017/9/5
 */
public class TestDemo2 {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setName("aaa");
        a.setState(1);
        BeanUtils.copyProperties(a, b);
        System.out.println(b);
    }

    private static class A {
        private Integer state;
        private String name;

        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static class B {
        private int state;
        private String name;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("B{");
            sb.append("state=").append(state);
            sb.append(", name='").append(name).append('\'');
            sb.append('}');
            return sb.toString();
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
