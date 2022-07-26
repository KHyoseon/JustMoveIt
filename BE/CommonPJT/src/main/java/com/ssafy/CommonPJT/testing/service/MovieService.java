package com.ssafy.CommonPJT.testing.service;

import com.ssafy.CommonPJT.testing.item.Movie;
import com.ssafy.CommonPJT.testing.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(m -> movies.add(m));
        return movies;
    }
}
