package com.ssafy.CommonPJT.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;

    private String phoneNumber;

    private String seat;

    @Enumerated(EnumType.STRING)
    private Classification classification;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "movieplayinginfo_id")
    private MoviePlayingInfo moviePlayingInfo;

    private LocalDateTime reservationTime;
}
