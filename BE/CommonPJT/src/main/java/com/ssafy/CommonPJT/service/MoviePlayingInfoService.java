package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoResDto;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoSaveDto;
import com.ssafy.CommonPJT.repository.MoviePlayingInfoRepository;
import com.ssafy.CommonPJT.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
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
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("영화가 존재하지 않습니다."));

        int runningTime = Integer.parseInt(movie.getRunningTime().replace("분", ""));
        int startMinute = Integer.parseInt(requestDto.getStartTime().substring(3));
        int hour = runningTime / 60;
        int minutes = runningTime % 60;
        int endHour = Integer.parseInt(requestDto.getStartTime().substring(0, 2));
        int endMinute = Integer.parseInt(requestDto.getStartTime().substring(3));
        String hourEnd = "";
        String minuteEnd = "";

        if (startMinute + minutes >= 60) {
            endHour += 1;
            endMinute += minutes - 60;
            endHour += hour;
        } else {
            endMinute += minutes;
            endHour += hour;
        }

        if (endHour >= 24) {
            endHour -= 24;
        }

        if (Integer.toString(endHour).length() < 2) {
            hourEnd = "0" + Integer.toString(endHour);
        } else {
            hourEnd = Integer.toString(endHour);
        }

        if (Integer.toString(endMinute).length() < 2) {
            minuteEnd = "0" + Integer.toString(endMinute);
        } else {
            minuteEnd = Integer.toString(endMinute);
        }

        String endTime = hourEnd + ":" + minuteEnd;
        MoviePlayingInfo saveInfo = requestDto.toEntity(movie, endTime);
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

    @Transactional
    public void deleteById(Long movieInfoId) {
        moviePlayingInfoRepository.deleteById(movieInfoId);
    }
}
