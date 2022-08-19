package com.ssafy.CommonPJT.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(length = 15)
    private String movieCode;

    @Column(length = 20)
    private String country;

    @Column(length = 100)
    private String genre;

    @Lob
    private String summary;

    @Column(length = 20)
    private String runningTime;

    @Column(length = 2000)
    private String img;

    @Column(length = 2000)
    private String img2;

    @Column(length = 2000)
    private String img3;

    @Column(length = 2000)
    private String img4;

    @Column(length = 2000)
    private String img5;

    @Column(length = 2000)
    private String img6;

    @Column(length = 20)
    private String rating;

    @Column(length = 100)
    private String engTitle;

    @Column(length = 15)
    private String ageLimit;

    private String releaseDate;

    @Column(length = 200)
    private String director;

    @Column(length = 200)
    private String actor;

    @Column(length = 15)
    private String totalCustomer;

    @JsonBackReference
    @OneToMany(mappedBy = "movie", cascade = ALL, orphanRemoval = true)
    private List<MoviePlayingInfo> moviePlayingInfo = new ArrayList<>();
}
