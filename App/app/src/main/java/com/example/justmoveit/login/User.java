package com.example.justmoveit.login;

import com.kakao.usermgmt.response.model.AgeRange;
import com.kakao.usermgmt.response.model.Gender;

import java.io.Serializable;

public class User implements Serializable {
    private String imgUrl;
    private String name;
    private String email;
    private Gender gender;
    private AgeRange age;

    public User() {
    }

    public User(String imgUrl, String name, String email, Gender gender, AgeRange age) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AgeRange getAge() {
        return age;
    }

    public void setAge(AgeRange age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
