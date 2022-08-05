package com.example.justmoveit.model;

import java.io.Serializable;
import java.util.Objects;

public class ReservedTicket implements Serializable, Comparable<ReservedTicket> {
    private String title;
    private String reservedDate;
    private String viewingDate;
    private String viewingTime;
    private String theater;
    private int adult;
    private int teen;
    private String seat;
    private Long priority;
    private boolean expired;

    public ReservedTicket(String title, String reservedDate, String viewingDate, String viewingTime, String theater, int adult, int teen, String seat) {
        this.title = title;
        this.reservedDate = reservedDate;
        this.viewingDate = viewingDate;
        this.viewingTime = viewingTime;
        this.theater = theater;
        this.adult = adult;
        this.teen = teen;
        this.seat = seat;

        String temp = viewingDate + viewingTime;
        this.priority = Long.parseLong(temp.replace("-", "").replace(":", ""));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewingDate() {
        return viewingDate;
    }

    public void setViewingDate(String viewingDate) {
        this.viewingDate = viewingDate;
    }

    public String getViewingTime() {
        return viewingTime;
    }

    public void setViewingTime(String viewingTime) {
        this.viewingTime = viewingTime;
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getTeen() {
        return teen;
    }

    public void setTeen(int teen) {
        this.teen = teen;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public int compareTo(ReservedTicket ticket2) {
        if (Objects.equals(this.getPriority(), ticket2.getPriority())) return 0;
        return (ticket2.getPriority() > this.getPriority() ? 1 : -1);
    }
}
