package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.dto.req.MovieDto;
import com.ssafy.CommonPJT.dto.res.MovieResDto;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public void save(MovieDto requestDto) {
        Movie saveMovie = requestDto.toEntity();
        movieRepository.save(saveMovie);
    }

    @Transactional(readOnly = true)
    public List<MovieResDto> findMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResDto> movie1 = movies.stream().map(MovieResDto::new).collect(Collectors.toList());
        return movie1;
    }

    @Transactional(readOnly = true)
    public MovieResDto findOne(Long id) {
        MovieResDto movie1 = new MovieResDto(movieRepository.findById(id).get());
        return movie1;
    }
}