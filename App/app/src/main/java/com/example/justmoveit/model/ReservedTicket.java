package com.example.justmoveit.model;

import java.io.Serializable;
import java.util.Objects;

public class ReservedTicket implements Serializable, Comparable<ReservedTicket> {
    private Long ticketId;
    private String title;
    private String viewingDate;
    private String seat;
    private int adult, child;

    private String viewingTime;
    private int theaterNo;

    private String priority;
    private boolean expired;

    public ReservedTicket(Ticket ticket) {
        this.ticketId = ticket.getTicketId();
        this.title = ticket.getMovieTitle();
        this.viewingDate = ticket.getReservationTime().replace("-", "");

        this.viewingTime = ticket.getStartTime();
        this.theaterNo = ticket.getTheaterNo();

        adult = child = 0;
        getParseClassification(ticket);
        this.seat = ticket.getSeat();

        String temp = viewingDate + viewingTime;
        this.priority = temp.replace("-", "").replace(":", "");
    }

    private void getParseClassification(Ticket ticket) {
        String[] parsed = ticket.getClassification().split(",");
        for(String s: parsed){
            switch (s){
                case "ADULT":
                    ++adult; break;
                case "CHILD":
                    ++child; break;
            }
        }
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
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
        return (Long.parseLong(ticket2.getPriority()) > Long.parseLong(this.getPriority()) ? 1 : -1);
    }

    public boolean isGreaterThan(String pri){
        return Long.parseLong(this.getPriority()) > Long.parseLong(pri);
    }
}
