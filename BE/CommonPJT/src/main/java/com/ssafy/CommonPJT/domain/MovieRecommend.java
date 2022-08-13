package com.ssafy.CommonPJT.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MovieRecommend {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "movieRecommend_id")
    private Long id;

    private String gender;

    private String age;
}
