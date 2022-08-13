package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.MovieRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRecommendRepository extends JpaRepository<MovieRecommend, Long> {
}
