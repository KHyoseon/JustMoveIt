package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.AgePortion;
import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.dto.Movie.AgePortionDto;
import com.ssafy.CommonPJT.dto.Movie.MovieRecommendDto;
import com.ssafy.CommonPJT.repository.AgePortionRepository;
import com.ssafy.CommonPJT.repository.MovieRecommendRepository;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieRecommendService {

    private final MovieRecommendRepository movieRecommendRepository;
    private final AgePortionRepository agePortionRepository;
    private final MovieRepository movieRepository;

    // 나이, 연령별 영화 추천
    public List<Movie> rank(MovieRecommendDto movieRecommendDto) {
        List<Movie> recommendation = new ArrayList<>();
        if (movieRecommendDto.getGender().equals("Male")) {
            if (movieRecommendDto.getAge().substring(0, 1).equals("2")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "male20"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getMale20().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else if (movieRecommendDto.getAge().substring(0, 1).equals("3")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "male30"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getMale30().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else if (movieRecommendDto.getAge().substring(0, 1).equals("4")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "male40"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getMale40().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else if (movieRecommendDto.getAge().substring(0, 1).equals("5")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "male50"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getMale50().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "male10"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getMale10().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        if (!movie.getAgeLimit().equals("청소년 관람불가")) {
                            if (movie.getAgeLimit().equals("전체 관람가")) {
                                recommendation.add(movie);
                            } else if (Integer.parseInt(movie.getAgeLimit().substring(0, 2)) <= Integer.parseInt(movieRecommendDto.getAge())) {
                                recommendation.add(movie);
                            }
                        }
                    }
                }
            }
        } else {
            if (movieRecommendDto.getAge().substring(0, 1).equals("2")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "female20"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getFemale20().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else if (movieRecommendDto.getAge().substring(0, 1).equals("3")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "female30"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getFemale30().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else if (movieRecommendDto.getAge().substring(0, 1).equals("4")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "female40"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getFemale40().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else if (movieRecommendDto.getAge().substring(0, 1).equals("5")) {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "female50"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getFemale50().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        recommendation.add(movie);
                    }
                }
            } else {
                List<AgePortion> agePortionList = agePortionRepository.findAll(Sort.by(Sort.Direction.DESC, "female10"));
                for (AgePortion agePortion : agePortionList) {
                    if (!agePortion.getFemale10().equals("0")) {
                        String code = agePortion.getMovieCode();
                        Movie movie = movieRepository.findMovieByMovieCode(code);
                        if (!movie.getAgeLimit().equals("청소년 관람불가")) {
                            if (movie.getAgeLimit().equals("전체 관람가")) {
                                recommendation.add(movie);
                            } else if (Integer.parseInt(movie.getAgeLimit().substring(0, 2)) < Integer.parseInt(movieRecommendDto.getAge())) {
                                recommendation.add(movie);
                            }
                        }
                    }
                }
            }
        }
        return recommendation;
    }

    // 전체 데이터 조회
    @GetMapping("/ageportion")
    public List<AgePortionDto> getAgePortion() {
        List<AgePortion> agePortionDto = agePortionRepository.findAll();
        List<AgePortionDto> agePortionDtoList = agePortionDto.stream().map(AgePortionDto::new).collect(Collectors.toList());
        return agePortionDtoList;
    }
}
