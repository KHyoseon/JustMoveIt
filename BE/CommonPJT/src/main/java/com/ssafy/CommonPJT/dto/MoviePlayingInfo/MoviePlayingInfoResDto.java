package com.ssafy.CommonPJT.dto.MoviePlayingInfo;

import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.dto.Ticket.TicketResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoviePlayingInfoResDto {

    private Long moviePlayingInfoId;
    private String theaterNo;
    private String startTime;
    private String endTime;
    private Long movieId;
    private String movieTitle;
    private String ageLimit;
    private List<TicketResDto> tickets = new ArrayList<>();


    public MoviePlayingInfoResDto(MoviePlayingInfo moviePlayingInfo) {
        this.moviePlayingInfoId = moviePlayingInfo.getId();
        this.theaterNo = moviePlayingInfo.getTheaterNo();
        this.startTime = moviePlayingInfo.getStartTime();
        this.endTime = moviePlayingInfo.getEndTime();
        this.movieId = moviePlayingInfo.getMovie().getId();
        this.ageLimit = moviePlayingInfo.getMovie().getAgeLimit();
        this.movieTitle = moviePlayingInfo.getMovie().getTitle();
        this.tickets = moviePlayingInfo.getTickets().stream().map(TicketResDto::new).collect(Collectors.toList());
    }
}
