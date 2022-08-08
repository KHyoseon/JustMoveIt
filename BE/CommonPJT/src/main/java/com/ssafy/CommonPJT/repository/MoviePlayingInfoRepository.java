package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePlayingInfoRepository extends JpaRepository<MoviePlayingInfo, Long> {
}
