package com.example.justmoveit.model;

import java.io.Serializable;

public class time implements Serializable {
    String time;

    @Override
    public String toString() {
        return "time{" +
                "time='" + time + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
