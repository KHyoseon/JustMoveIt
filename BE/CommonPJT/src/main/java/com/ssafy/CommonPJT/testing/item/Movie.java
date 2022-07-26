package com.ssafy.CommonPJT.testing.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="movie")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "male10", length = 5)
    private String male10;

    @Column(name = "male20", length = 5)
    private String male20;

    @Column(name = "male30", length = 5)
    private String male30;

    @Column(name = "male40", length = 5)
    private String male40;

    @Column(name = "male50", length = 5)
    private String male50;

    @Column(name = "female10", length = 5)
    private String female10;

    @Column(name = "female20", length = 5)
    private String female20;

    @Column(name = "female30", length = 5)
    private String female30;

    @Column(name = "female40", length = 5)
    private String female40;

    @Column(name = "female50", length = 5)
    private String female50;

    @Column(name = "ranking", length = 5)
    private String ranking;

    @Column(name = "movieCode", length = 15, nullable = false)
    private String movieCode;

    @OneToOne
    @JoinColumn(name = "detailId", referencedColumnName = "detailId")
    private Detail detail;


}
