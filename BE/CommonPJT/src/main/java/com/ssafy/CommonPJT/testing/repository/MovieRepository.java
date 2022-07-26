package com.ssafy.CommonPJT.testing.repository;

import com.ssafy.CommonPJT.testing.item.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
