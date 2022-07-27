package com.ssafy.CommonPJT.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Detail {

    @Id
    @GeneratedValue
    @Column(name = "detail_id")
    private Long id;

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

    @Column(length = 20)
    private String rating;

    @Column(length = 100)
    private String engTitle;

    @Column(length = 15)
    private String filmRating;

    private LocalDateTime releaseDate;

    @Column(length = 200)
    private String director;

    @Column(length = 200)
    private String actor;

    @Column(length = 15)
    private String totalCustomer;

    @OneToOne(mappedBy = "detail", fetch = LAZY)
    private Movie movie;
}
