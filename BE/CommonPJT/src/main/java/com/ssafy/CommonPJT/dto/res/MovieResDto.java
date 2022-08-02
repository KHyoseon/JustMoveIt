package com.ssafy.CommonPJT.dto.res;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResDto {

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

    private List<MoviePlayingInfo> moviePlayingInfo = new ArrayList<>();


    public MovieResDto(Movie movie) {
        this.title = movie.getTitle();
        this.movieCode = movie.getMovieCode();
        this.country = movie.getCountry();
        this.genre = movie.getGenre();
        this.summary = movie.getSummary();
        this.runningTime = movie.getRunningTime();
        this.img = movie.getImg();
        this.rating = movie.getRating();
        this.ageLimit = movie.getAgeLimit();
        this.engTitle = movie.getEngTitle();
        this.releaseDate = movie.getReleaseDate();
        this.director = movie.getDirector();
        this.actor = movie.getActor();
        this.totalCustomer = movie.getTotalCustomer();
        this.moviePlayingInfo = movie.getMoviePlayingInfo();
    }
}
