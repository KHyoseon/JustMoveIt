package com.example.justmoveit.model;

import java.io.Serializable;

public class MoviePlayingInfo implements Serializable {
    private Long moviePlayingInfoId;
    private Long movieId;
    private String movieTitle;
    private String ageLimit;
    private String startTime;
    private String endTime;
    private String theaterNo;
    private Ticket[] tickets;

    public MoviePlayingInfo(Long moviePlayingInfoId, Long movieId, String movieTitle, String ageLimit, String startTime, String endTime, String theaterNo, Ticket[] tickets) {
        this.moviePlayingInfoId = moviePlayingInfoId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.ageLimit = ageLimit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.theaterNo = theaterNo;
        this.tickets = tickets;
    }

    public Long getMoviePlayingInfoId() {
        return moviePlayingInfoId;
    }

    public void setMoviePlayingInfoId(Long moviePlayingInfoId) {
        this.moviePlayingInfoId = moviePlayingInfoId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTheaterNo() {
        return theaterNo;
    }

    public void setTheaterNo(String theaterNo) {
        this.theaterNo = theaterNo;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    public boolean isPassedNow(String time){
        return Integer.parseInt(this.startTime.replace(":", "")) < Integer.parseInt(time);
    }
}
