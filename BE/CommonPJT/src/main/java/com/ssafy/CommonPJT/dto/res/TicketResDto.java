package com.ssafy.CommonPJT.dto.res;

import com.ssafy.CommonPJT.domain.Classification;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
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
    private LocalDateTime reservationTime;
    private MoviePlayingInfo moviePlayingInfo;



    public TicketResDto(Ticket ticket) {
        this.phoneNumber = ticket.getPhoneNumber();
        this.seat = ticket.getSeat();
        this.classification = ticket.getClassification();
        this.reservationTime = ticket.getReservationTime();
        this.moviePlayingInfo = ticket.getMoviePlayingInfo();
    }
}
