package com.tinet.clink.openapi;

import org.junit.Test;


/**
 *
 * @author liuhy
 * @date: 2020/12/8
 **/
public class Test1 {


    @Test
    public void test(){


    }


    class User {

        private String name;

        private Integer age;

        private String address;

        private String tel;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    ", tel='" + tel + '\'' +
                    '}';
        }
    }
}