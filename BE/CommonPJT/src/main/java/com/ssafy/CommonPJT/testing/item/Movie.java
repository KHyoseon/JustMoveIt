package com.ssafy.CommonPJT.testing.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 5)
    private String male10;

    @Column(length = 5)
    private String male20;

    @Column(length = 5)
    private String male30;

    @Column(length = 5)
    private String male40;

    @Column(length = 5)
    private String male50;

    @Column(length = 5)
    private String female10;

    @Column(length = 5)
    private String female20;

    @Column(length = 5)
    private String female30;

    @Column(length = 5)
    private String female40;

    @Column(length = 5)
    private String female50;

    @Column(length = 5)
    private String ranking;

    @Column(length = 15, nullable = false)
    private String movieCode;
}
