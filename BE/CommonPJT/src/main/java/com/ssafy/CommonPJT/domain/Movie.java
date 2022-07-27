package com.ssafy.CommonPJT.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String title;

    @Column(length = 10)
    private String male10;
    @Column(length = 10)
    private String male20;
    @Column(length = 10)
    private String male30;
    @Column(length = 10)
    private String male40;
    @Column(length = 10)
    private String male50;

    @Column(length = 10)
    private String female10;
    @Column(length = 10)
    private String female20;
    @Column(length = 10)
    private String female30;
    @Column(length = 10)
    private String female40;
    @Column(length = 10)
    private String female50;

    @Column(length = 5)
    private String ranking;

    @Column(length = 15)
    private String movieCode;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "detail_id")
    private Detail detail;
}
