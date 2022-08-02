package com.ssafy.CommonPJT.dto.req;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoviePlayingInfoDto {

    private String theaterNo;

    private String startTime;

    private String endTime;

    @Builder
    public MoviePlayingInfo toEntity(Movie movie) {
        return MoviePlayingInfo.builder()
                .theaterNo(this.theaterNo)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .movie(movie)
                .build();
    }
}
