package com.ssafy.CommonPJT.dto;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;

import java.time.LocalDateTime;

public class MovieDTO {

    private Long id;

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

    private LocalDateTime releaseDate;

    private String director;

    private String actor;

    private String totalCustomer;

    private MoviePlayingInfo moviePlayingInfo;

    public MovieDTO(Movie movie) {
    }

}
