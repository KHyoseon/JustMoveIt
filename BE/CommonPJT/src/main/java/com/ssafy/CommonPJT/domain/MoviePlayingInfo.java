package com.ssafy.CommonPJT.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoviePlayingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieplayinginfo_id")
    private Long id;

    private String theaterNo;

    private String startTime;

    private String endTime;

    @JsonManagedReference
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @JsonBackReference
    @OneToMany(mappedBy = "moviePlayingInfo", cascade = ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();
}
