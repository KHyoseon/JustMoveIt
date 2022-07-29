package com.ssafy.CommonPJT.domain;

import lombok.Getter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class MoviePlayingInfo {

    @Id
    @GeneratedValue
    @Column(name = "movieplayinginfo_id")
    private Long id;

    private String theaterNo;

    private String startTime;

    private String endTime;

    @OneToOne(mappedBy = "moviePlayingInfo", fetch = LAZY, cascade = ALL)
    private Movie movie;

    @OneToMany(mappedBy = "moviePlayingInfo", cascade = ALL)
    private List<Ticket> tickets = new ArrayList<>();
}
