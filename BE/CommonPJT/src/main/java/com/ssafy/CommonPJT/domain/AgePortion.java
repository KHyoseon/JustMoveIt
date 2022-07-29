package com.ssafy.CommonPJT.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class AgePortion {

    @Id
    @GeneratedValue
    @Column(name = "ageportion_id")
    private Long id;

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

    private String ranking;

    private String movieCode;
}
