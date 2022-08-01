package com.ssafy.CommonPJT.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Lob
    private String kakaoToken;

    @Column(length = 25)
    private String phoneNumber;

    @Column(length = 15)
    private String username;

    private String profileImg;
}
