package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.domain.Movie;
import com.ssafy.CommonPJT.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api("MovieController") //swagger 컨트롤러 이름
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @ApiOperation(value = "영화 목록", notes = "영화 목록을 출력한다.", response = List.class)
    @GetMapping("/")
    public List<Movie> getMovieList() {
        return movieService.findMovies();
    }

    @ApiOperation(value = "영화", notes = "영화 정보를 출력한다.", response = Movie.class)
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.findOne(id);
    }
}
