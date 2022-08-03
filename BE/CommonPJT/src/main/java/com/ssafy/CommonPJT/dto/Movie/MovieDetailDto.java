package com.ssafy.CommonPJT.dto.Movie;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailDto {

    private String title;

    private String movieCode;

    private String country;

    private String genre;

    private String summary;

    private String runningTime;

    private String img;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;

    private String rating;

    private String engTitle;

    private String ageLimit;

    private String releaseDate;

    private String director;

    private String actor;

    private String totalCustomer;

    private List<MoviePlayingInfoResDto> moviePlayingInfoList = new ArrayList<>();


    public MovieDetailDto(Movie movie) {
        this.title = movie.getTitle();
        this.movieCode = movie.getMovieCode();
        this.country = movie.getCountry();
        this.genre = movie.getGenre();
        this.summary = movie.getSummary();
        this.runningTime = movie.getRunningTime();
        this.img = movie.getImg();
        this.img2 = movie.getImg2();
        this.img3 = movie.getImg3();
        this.img4 = movie.getImg4();
        this.img5 = movie.getImg5();
        this.img6 = movie.getImg6();
        this.rating = movie.getRating();
        this.ageLimit = movie.getAgeLimit();
        this.engTitle = movie.getEngTitle();
        this.releaseDate = movie.getReleaseDate();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
        this.totalCustomer = movie.getTotalCustomer();
        this.moviePlayingInfoList = movie.getMoviePlayingInfo().stream().map(MoviePlayingInfoResDto::new).collect(Collectors.toList());
    }
}
