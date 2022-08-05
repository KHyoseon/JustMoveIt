package com.ssafy.CommonPJT.dto.Movie;

import com.ssafy.CommonPJT.domain.MovieRecommend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRecommendDto {

    String gender;

    String age;

    public MovieRecommend toEntity() {
        return MovieRecommend.builder()
                .gender(this.gender)
                .age(this.age)
                .build();
    }
}
