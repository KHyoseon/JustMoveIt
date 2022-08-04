package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.dto.Movie.MovieRecommedDto;
import com.ssafy.CommonPJT.service.MovieRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@Api("MovieRecommendController")
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class MovieRecommendController {

    private final MovieRecommendService movieRecommendService;

    @ApiOperation(value = "영화 추천", notes = "데이터 기반의 영화를 추천해준다.")
    @PostMapping
    public List<String> rank(MovieRecommedDto movieRecommedDto) {
        log.info("영화 추천 리스트를 조회합니다.");
        return movieRecommendService.rank(movieRecommedDto);
    }
}
