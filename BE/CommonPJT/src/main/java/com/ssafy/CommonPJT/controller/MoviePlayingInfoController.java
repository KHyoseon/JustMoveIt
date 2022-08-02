package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.dto.req.MoviePlayingInfoDto;
import com.ssafy.CommonPJT.service.MoviePlayingInfoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("MoviePlayingInfoController")
@RequestMapping("/info")
public class MoviePlayingInfoController {

    private final MoviePlayingInfoService moviePlayingInfoService;

    @GetMapping("/")
    public ResponseEntity<?> getInfoList() {
        return new ResponseEntity<>(moviePlayingInfoService.findInfo(), HttpStatus.OK);
    }

    @PostMapping("/{movieId}")
    public void saveInfo(@RequestBody MoviePlayingInfoDto moviePlayingInfoDto, @PathVariable Long movieId) {
        moviePlayingInfoService.save(moviePlayingInfoDto, movieId);
    }
}
