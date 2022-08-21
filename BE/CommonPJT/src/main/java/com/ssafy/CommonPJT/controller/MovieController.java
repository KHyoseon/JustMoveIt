package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.dto.Movie.MovieDetailDto;
import com.ssafy.CommonPJT.dto.Movie.MovieSaveDto;
import com.ssafy.CommonPJT.service.MovieService;
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
@RequiredArgsConstructor
@Api("MovieController") //swagger 컨트롤러 이름
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @ApiOperation(value = "영화 저장", notes = "영화 정보를 저장한다.")
    @PostMapping
    public void save(@RequestBody MovieSaveDto requestDto) {
        movieService.save(requestDto);
        log.info("영화를 저장합니다.");
    }

    @ApiOperation(value = "영화 정보 목록", notes = "영화 정보 리스트 출력한다.")
    @GetMapping
    public ResponseEntity<List<MovieDetailDto>> getMovieList() {       // response.data <<
        log.info("영화 정보 리스트를 조회합니다.");
        return new ResponseEntity<>(movieService.findMovies(), HttpStatus.OK);
    }

    @ApiOperation(value = "영화 정보(영화코드)", notes = "영화 코드에 따른 영화정보를 출력한다.")
    @GetMapping("/{movieCode}")
    public ResponseEntity<MovieDetailDto> getMovieByMovieCode(@PathVariable String movieCode) {
        log.info("영화코드에 따른 영화정보를 출력합니다.");
        return new ResponseEntity<>(movieService.findByMovieCode(movieCode), HttpStatus.OK);
    }

    @ApiOperation(value = "영화 정보 삭제", notes = "영화 정보를 삭제합니다.")
    @DeleteMapping("/{movieCode}")
    public void deleteMovieByMovieCode(@PathVariable String movieCode) {
        movieService.deleteByMovieCode(movieCode);
    }
}
