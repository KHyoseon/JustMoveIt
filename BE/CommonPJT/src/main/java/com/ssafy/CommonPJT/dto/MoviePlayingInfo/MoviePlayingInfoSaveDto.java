package com.ssafy.CommonPJT.dto.MoviePlayingInfo;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MoviePlayingInfoSaveDto {

    @Schema(description = "상영관 번호")
    private String theaterNo;

    @Schema(description = "시작 시간")
    private String startTime;

    @Schema(description = "영화 ID")
    private Long movieId;

    @Builder
    public MoviePlayingInfo toEntity(Movie movie, String endTime) {
        return MoviePlayingInfo.builder()
                .theaterNo(this.theaterNo)
                .startTime(this.startTime)
                .endTime(endTime)
                .movie(movie)
                .build();
    }
}
