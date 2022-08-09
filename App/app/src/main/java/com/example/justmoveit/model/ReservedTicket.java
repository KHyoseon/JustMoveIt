package com.example.justmoveit.model;

import java.io.Serializable;
import java.util.Objects;

public class ReservedTicket implements Serializable, Comparable<ReservedTicket> {
    private final Ticket ticket;
    private int adult, child;
    private boolean expired;

    public ReservedTicket(Ticket ticket) {
        this.ticket = ticket;
        adult = child = 0;
        getParseClassification();
    }

    private void getParseClassification() {
        String[] parsed = this.ticket.getClassification().split(",");
        for(String s: parsed){
            switch (s){
                case "ADULT":
                    ++adult; break;
                case "CHILD":
                    ++child; break;
            }
        }
    }

    public Ticket getTicket() {
        return ticket;
    }

    public int getAdult() {
        return adult;
    }

    public int getChild() {
        return child;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public int compareTo(ReservedTicket ticket2) {
        String thisPri = this.getTicket().getReservationTime();
        String otherPri = ticket2.getTicket().getReservationTime();
        return (thisPri.compareTo(otherPri));
    }

    public boolean isPassedNow(String otherPri){
        String thisPri = this.getTicket().getReservationTime();
        return thisPri.compareTo(otherPri) > 0;
    }
}
