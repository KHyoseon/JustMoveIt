package com.example.justmoveit.model;

import java.io.Serializable;

// 서버 통신 dto
public class Ticket implements Serializable {
    private Long ticketId;
    private Long moviePlayingInfoId;
    private Long movieId;
    private String movieTitle;
    private String ageLimit;
    private String startTime;
    private String endTime;
    private String phoneNumber;
    private String classification;
    private String reservationTime; // yyyy-MM-dd
    private String seat;
    private int theaterNo, totalCost;

    public Ticket(Long moviePlayingInfoId, Long movieId, String movieTitle, String ageLimit, String startTime, String endTime,
                  String phoneNumber, String classification, String reservationTime, String seat, int theaterNo, int totalCost) {
        this.moviePlayingInfoId = moviePlayingInfoId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.ageLimit = ageLimit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.phoneNumber = phoneNumber;
        this.classification = classification;
        this.reservationTime = reservationTime;
        this.seat = seat;
        this.theaterNo = theaterNo;
        this.totalCost = totalCost;
    }
    public Ticket(Long ticketId, Long moviePlayingInfoId, Long movieId, String movieTitle, String ageLimit, String startTime, String endTime,
                  String phoneNumber, String classification, String reservationTime, String seat, int theaterNo, int totalCost) {
        this.ticketId = ticketId;
        this.moviePlayingInfoId = moviePlayingInfoId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.ageLimit = ageLimit;
        this.startTime = startTime;
        this.endTime = endTime;
        this.phoneNumber = phoneNumber;
        this.classification = classification;
        this.reservationTime = reservationTime;
        this.seat = seat;
        this.theaterNo = theaterNo;
        this.totalCost = totalCost;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public int getTheaterNo() {
        return theaterNo;
    }

    public void setTheaterNo(int theaterNo) {
        this.theaterNo = theaterNo;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
