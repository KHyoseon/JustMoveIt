package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.dto.Movie.AgePortionDto;
import com.ssafy.CommonPJT.dto.Movie.MovieRecommendDto;
import com.ssafy.CommonPJT.service.MovieRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api("MovieRecommendController")
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class MovieRecommendController {

    private final MovieRecommendService movieRecommendService;

    @ApiOperation(value = "영화 추천", notes = "데이터 기반의 영화를 추천해준다.")
    @PostMapping
    public ResponseEntity<List<Movie>> rank(@RequestBody MovieRecommendDto movieRecommendDto) {
        log.info("영화 추천 리스트를 조회합니다.");
        return new ResponseEntity<>(movieRecommendService.rank(movieRecommendDto), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<AgePortionDto>> getAgePortion() {
        return new ResponseEntity<>(movieRecommendService.getAgePortion(), HttpStatus.OK);
    }
}
