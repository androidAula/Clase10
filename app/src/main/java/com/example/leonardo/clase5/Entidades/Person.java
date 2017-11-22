package com.example.leonardo.clase5.Entidades;

import java.io.Serializable;

/**
 * Created by Leonardo on 20/11/2017.
 */

public class Person implements Serializable{

    private String iD;
    private String firstname;
    private String lastname;
    private Integer age;
    private String phone;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}



