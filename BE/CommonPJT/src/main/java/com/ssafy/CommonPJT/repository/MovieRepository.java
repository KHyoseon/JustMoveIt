package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    public Movie findMovieByMovieCode(String movieCode);

    public Movie deleteByMovieCode(String movieCode);
}
