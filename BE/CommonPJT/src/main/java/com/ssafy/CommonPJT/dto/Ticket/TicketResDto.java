package com.ssafy.CommonPJT.dto.Ticket;

import com.ssafy.CommonPJT.domain.Classification;
import com.ssafy.CommonPJT.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketResDto {

    private String phoneNumber;
    private String seat;
    private Classification classification;
    private Long moviePlayingInfoId;
    private Long movieId;
    private String movieTitle;
    private String startTime;
    private String endTime;
    private String ageLimit;
    private LocalDateTime reservationTime;


    public TicketResDto(Ticket ticket) {
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
    }
}
