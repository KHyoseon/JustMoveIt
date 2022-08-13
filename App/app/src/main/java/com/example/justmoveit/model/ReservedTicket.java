package com.example.justmoveit.model;

import android.util.Log;

import java.io.Serializable;
import java.util.Objects;

public class ReservedTicket implements Serializable, Comparable<ReservedTicket> {
    private final Ticket ticket;
    private boolean expired;

    public ReservedTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public static int[] convertClassificationToInt(String str) {
        String[] parsed = str.split(",");
        int[] ret = new int[2];
        for(String s: parsed){
            if ("ADULT".equals(s)) {
                ++ret[0];
            } else {
                ++ret[1];
            }
        }
        return ret;
    }

    public static String convertClassificationToString(int[] param) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< param[0]; i++){
            sb.append("ADULT,");
        }
        for(int i=0; i< param[1]; i++){
            sb.append("CHILD,");
        }
        sb.setLength(sb.length()-1);
        return sb.toString();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public int compareTo(ReservedTicket ticket2) {
        String thisPri = ticket.getReservationTime();
        String otherPri = ticket2.getTicket().getReservationTime();
        return (thisPri.compareTo(otherPri));
    }

    public boolean isPassedNow(String otherPri){
        String thisPri = (ticket.getReservationTime().split(" "))[0] + " " + ticket.getStartTime();
        Log.i("is expired?", "this: "+ thisPri +" / now: "+otherPri);
        return thisPri.compareTo(otherPri) < 0;
    }
}
