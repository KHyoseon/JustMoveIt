package com.example.justmoveit.model;

import java.io.Serializable;
import java.util.Objects;

public class ReservedTicket implements Serializable, Comparable<ReservedTicket> {
    private String title;
    private String viewingDate;
    private String seat;
    private int adult, child, disabled;

    private String viewingTime;
    private int theaterNo;

    private Long priority;
    private boolean expired;

    public ReservedTicket(Ticket ticket) {
        this.title = ticket.getMovieTitle();
        this.viewingDate = ticket.getReservationTime().substring(0, 10);

        this.viewingTime = ticket.getStartTime();
        this.theaterNo = ticket.getTheaterNo();

        adult = child = disabled = 0;
        getParseClassification(ticket);
        this.seat = ticket.getSeat();

        String temp = viewingDate + viewingTime;
        this.priority = Long.parseLong(temp.replace("-", "").replace(":", ""));
    }

    private void getParseClassification(Ticket ticket) {
        String[] parsed = ticket.getClassification().split(",");
        for(String s: parsed){
            switch (s){
                case "ADULT":
                    ++adult; break;
                case "CHILD":
                    ++child; break;
                case "DISABLED":
                    ++disabled; break;
            }
        }
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

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public String getViewingTime() {
        return viewingTime;
    }

    public void setViewingTime(String viewingTime) {
        this.viewingTime = viewingTime;
    }

    public int getTheaterNo() {
        return theaterNo;
    }

    public void setTheaterNo(int theaterNo) {
        this.theaterNo = theaterNo;
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
