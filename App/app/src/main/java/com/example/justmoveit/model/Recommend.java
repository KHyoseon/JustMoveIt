package com.example.justmoveit.model;

import java.io.Serializable;

public class Recommend implements Serializable {
    String age;
    String gender;

    public Recommend(String age, String gender) {
        this.age = age;
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
