package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.AgePortion;
import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.dto.Movie.MovieRecommedDto;
import com.ssafy.CommonPJT.repository.AgePortionRepository;
import com.ssafy.CommonPJT.repository.MovieRecommendRepository;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieRecommendService {

    private final MovieRecommendRepository movieRecommendRepository;
    private final AgePortionRepository agePortionRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public List<String> rank(MovieRecommedDto movieRecommedDto) {
        List<String> recommendation = new ArrayList<>();
        if (movieRecommedDto.getGender().equals("Male")) {
            if (movieRecommedDto.getAge().charAt(0) == '1') {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "male10"));
                for (AgePortion agePortion : agePortionList) {
                    recommendation.add(agePortion.getMovieCode());
                }

            }
        }
    }
}
