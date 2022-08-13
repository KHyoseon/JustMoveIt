package com.ssafy.CommonPJT.dto.Ticket;

import com.ssafy.CommonPJT.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResDto {

    private Long ticketId;
    private String phoneNumber;
    private String seat;
    private String classification;
    private Long moviePlayingInfoId;
    private Long movieId;
    private String movieTitle;
    private String startTime;
    private String endTime;
    private String ageLimit;
    private Date reservationTime;
    private String totalCost;
    private String theaterNo;


    public TicketResDto(Ticket ticket) {
        this.ticketId = ticket.getId();
        this.phoneNumber = ticket.getPhoneNumber();
        this.seat = ticket.getSeat();
        this.classification = ticket.getClassification();
        this.moviePlayingInfoId = ticket.getMoviePlayingInfo().getId();
        this.movieId = ticket.getMoviePlayingInfo().getMovie().getId();
        this.movieTitle = ticket.getMoviePlayingInfo().getMovie().getTitle();
        this.startTime = ticket.getMoviePlayingInfo().getStartTime();
        this.endTime = ticket.getMoviePlayingInfo().getEndTime();
        this.ageLimit = ticket.getMoviePlayingInfo().getMovie().getAgeLimit();
        this.reservationTime = ticket.getReservationTime();
        this.totalCost = ticket.getTotalCost();
        this.theaterNo = ticket.getMoviePlayingInfo().getTheaterNo();
    }
}
