package com.ssafy.CommonPJT.dto.res;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MoviePlayingInfoResDto {

    private String theaterNo;
    private String startTime;
    private String endTime;
    private Movie movie;
    private List<Ticket> tickets = new ArrayList<>();


    public MoviePlayingInfoResDto(MoviePlayingInfo moviePlayingInfo) {
        this.theaterNo = moviePlayingInfo.getTheaterNo();
        this.startTime = moviePlayingInfo.getStartTime();
        this.endTime = moviePlayingInfo.getEndTime();
        this.movie = moviePlayingInfo.getMovie();
        this.tickets = moviePlayingInfo.getTickets();
    }
}
