package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoResDto;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoSaveDto;
import com.ssafy.CommonPJT.repository.MoviePlayingInfoRepository;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MoviePlayingInfoService {

    private final MoviePlayingInfoRepository moviePlayingInfoRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void save(MoviePlayingInfoSaveDto requestDto) {
        Long movieId = requestDto.getMovieId();
        Movie movie = movieRepository.findById(movieId).get();
        MoviePlayingInfo saveInfo = requestDto.toEntity(movie);
        moviePlayingInfoRepository.save(saveInfo);
    }

    public List<MoviePlayingInfoResDto> findInfo() {
        List<MoviePlayingInfo> moviePlayingInfoList = moviePlayingInfoRepository.findAll();
        List<MoviePlayingInfoResDto> infos = moviePlayingInfoList.stream().map(MoviePlayingInfoResDto::new).collect(Collectors.toList());
        return infos;
    }

    public MoviePlayingInfoResDto findById(Long movieplayinginfoId) {
        MoviePlayingInfoResDto info = new MoviePlayingInfoResDto(moviePlayingInfoRepository.findById(movieplayinginfoId).get());
        return info;
    }
}
