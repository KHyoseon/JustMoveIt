package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.dto.req.MoviePlayingInfoDto;
import com.ssafy.CommonPJT.dto.res.MoviePlayingInfoResDto;
import com.ssafy.CommonPJT.repository.MoviePlayingInfoRepository;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoviePlayingInfoService {

    private final MoviePlayingInfoRepository moviePlayingInfoRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void save(MoviePlayingInfoDto requestDto, Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        MoviePlayingInfo saveInfo = requestDto.toEntity(movie);
        moviePlayingInfoRepository.save(saveInfo);
    }

    @Transactional(readOnly = true)
    public List<MoviePlayingInfoResDto> findInfo() {
        List<MoviePlayingInfo> moviePlayingInfos = moviePlayingInfoRepository.findAll();
        List<MoviePlayingInfoResDto> infos = moviePlayingInfos.stream().map(MoviePlayingInfoResDto::new).collect(Collectors.toList());
        return infos;
    }
}
