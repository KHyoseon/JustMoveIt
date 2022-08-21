package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.dto.Movie.MovieDetailDto;
import com.ssafy.CommonPJT.dto.Movie.MovieSaveDto;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public void save(MovieSaveDto requestDto) {
        Movie saveMovie = requestDto.toEntity();
        movieRepository.save(saveMovie);
    }

    public List<MovieDetailDto> findMovies() {
        List<Movie> movies = movieRepository.findAll(); // 지양해야되는 습관 >> 리소스가 커졌을 때는 불필요한 데이터까지 다 가져옴
        List<MovieDetailDto> movie1 = movies.stream().map(MovieDetailDto::new).collect(Collectors.toList());
        return movie1;
    }

    public MovieDetailDto findOne(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 영화입니다."));
        MovieDetailDto movie1 = new MovieDetailDto(movie);
        return movie1;
    }

    public MovieDetailDto findByMovieCode(String movieCode) throws IllegalArgumentException{
        Movie movie = movieRepository.findMovieByMovieCode(movieCode);
        MovieDetailDto movie1 = new MovieDetailDto(movie);
        return movie1;
    }

    @Transactional
    public void deleteByMovieCode(String movieCode) {
        movieRepository.deleteByMovieCode(movieCode);
    }
}