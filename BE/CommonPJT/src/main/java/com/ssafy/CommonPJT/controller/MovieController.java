package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.dto.Movie.MovieDetailDto;
import com.ssafy.CommonPJT.dto.Movie.MovieSaveDto;
import com.ssafy.CommonPJT.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api("MovieController") //swagger 컨트롤러 이름
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @ApiOperation(value = "영화 저장", notes = "영화 정보를 저장한다.", response = Movie.class)
    @PostMapping
    public void save(@RequestBody MovieSaveDto requestDto) {
        movieService.save(requestDto);
    }

    @ApiOperation(value = "영화 목록", notes = "영화 목록을 출력한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<MovieDetailDto>> getMovieList() {       // response.data <<
        return new ResponseEntity<>(movieService.findMovies(), HttpStatus.OK);
    }

    @ApiOperation(value = "영화", notes = "영화 정보를 출력한다.", response = Movie.class)
    @GetMapping("/{id}")
    public MovieDetailDto getMovieById(@PathVariable Long id) {
        return movieService.findOne(id);
    }
}
