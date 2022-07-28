package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    // 영화 추가
    @Transactional
    public Long insert(Movie movie) {
        validateDuplicateMovie(movie);
        movieRepository.save(movie);
        return movie.getId();
    }

    private void validateDuplicateMovie(Movie movie) {
        List<Movie> findMovies = movieRepository.findByName(movie.getTitle());
        if (!findMovies.isEmpty()) {
            throw new IllegalStateException("Already exist movie!");
        }
    }

    // 영화 리스트 조회
    @Transactional(readOnly = true)
    public List<Movie> findMovies() {
        return movieRepository.findAll();
    }

    // 특정 영화 조회
    @Transactional(readOnly = true)
    public Movie findOne(Long movieId) {
        return movieRepository.findOne(movieId);
    }
}
