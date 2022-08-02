package com.ssafy.CommonPJT.dto.req;

import com.ssafy.CommonPJT.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private String title;

    private String movieCode;

    private String country;

    private String genre;

    private String summary;

    private String runningTime;

    private String img;

    private String rating;

    private String engTitle;

    private String ageLimit;

    private String releaseDate;

    private String director;

    private String actor;

    private String totalCustomer;


    @Builder
    public Movie toEntity() {
        return Movie.builder()
                .title(this.title)
                .movieCode(this.movieCode)
                .country(this.country)
                .genre(this.genre)
                .summary(this.summary)
                .runningTime(this.runningTime)
                .img(this.img)
                .rating(this.rating)
                .engTitle(this.engTitle)
                .ageLimit(this.ageLimit)
                .releaseDate(this.releaseDate)
                .director(this.director)
                .actor(this.actor)
                .totalCustomer(this.totalCustomer)
                .build();
    }


}