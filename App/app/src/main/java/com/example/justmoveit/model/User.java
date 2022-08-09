package com.example.justmoveit.model;

import java.io.Serializable;

public class User implements Serializable {
    private String imgUrl;
    private String name;
    private String email;
    private String gender;
    private String age;

    public User(String imgUrl, String name, String email, String gender, String age) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }
}
