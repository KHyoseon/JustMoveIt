package com.ssafy.CommonPJT.dto.Movie;

import com.ssafy.CommonPJT.domain.Movie;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSaveDto {

    @Schema(description = "타이틀")
    private String title;

    @Schema(description = "영화코드")
    private String movieCode;

    @Schema(description = "국가")
    private String country;

    @Schema(description = "장르")
    private String genre;

    @Schema(description = "줄거리")
    private String summary;

    @Schema(description = "영화 길이")
    private String runningTime;

    @Schema(description = "포스터")
    private String img;

    @Schema(description = "포스터2")
    private String img2;

    @Schema(description = "포스터3")
    private String img3;

    @Schema(description = "포스터4")
    private String img4;

    @Schema(description = "포스터5")
    private String img5;

    @Schema(description = "포스터6")
    private String img6;

    @Schema(description = "평점")
    private String rating;

    @Schema(description = "영어 타이틀")
    private String engTitle;

    @Schema(description = "연령 제한")
    private String ageLimit;

    @Schema(description = "개봉일")
    private String releaseDate;

    @Schema(description = "감독")
    private String director;

    @Schema(description = "주연배우")
    private String actor;

    @Schema(description = "관객수")
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
                .img2(this.img2)
                .img3(this.img3)
                .img4(this.img4)
                .img5(this.img5)
                .img6(this.img6)
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