package com.ssafy.CommonPJT.dto.Movie;

import com.ssafy.CommonPJT.domain.AgePortion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgePortionDto {

    private String male10;
    private String male20;
    private String male30;
    private String male40;
    private String male50;

    private String female10;
    private String female20;
    private String female30;
    private String female40;
    private String female50;

    private String ranking;

    private String movieCode;

    public AgePortionDto(AgePortion agePortion) {
        this.male10 = agePortion.getMale10();
        this.male20 = agePortion.getMale20();
        this.male30 = agePortion.getMale30();
        this.male40 = agePortion.getMale40();
        this.male50 = agePortion.getMale50();
        this.female10 = agePortion.getFemale10();
        this.female20 = agePortion.getFemale20();
        this.female30 = agePortion.getFemale30();
        this.female40 = agePortion.getFemale40();
        this.female50 = agePortion.getFemale50();
        this.ranking = agePortion.getRanking();
        this.movieCode = agePortion.getMovieCode();
    }
}


