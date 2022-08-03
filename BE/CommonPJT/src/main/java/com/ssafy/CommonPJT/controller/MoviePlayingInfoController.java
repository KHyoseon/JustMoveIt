package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.domain.MoviePlayingInfo;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoResDto;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoSaveDto;
import com.ssafy.CommonPJT.service.MoviePlayingInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api("MoviePlayingInfoController")
@RequestMapping("/info")
public class MoviePlayingInfoController {

    private final MoviePlayingInfoService moviePlayingInfoService;

    @ApiOperation(value = "전체 영화 상영 정보", notes = "전체 영화 상영 정보를 출력한다.", response = List.class)
    @GetMapping
    public ResponseEntity<List<MoviePlayingInfoResDto>> getInfoList() {
        return new ResponseEntity<>(moviePlayingInfoService.findInfo(), HttpStatus.OK);
    }

    @ApiOperation(value = "영화 상영 정보", notes = "영화 상영 정보를 출력한다.", response = MoviePlayingInfo.class)
    @GetMapping("/{movieplayinginfoId}")
    public MoviePlayingInfoResDto getInfo(@PathVariable Long movieplayinginfoId) {
        return moviePlayingInfoService.findById(movieplayinginfoId);
    }


    @ApiOperation(value = "영화 상영 정보 저장", notes = "영화 상영 정보를 저장한다.", response = MoviePlayingInfo.class)
    @PostMapping
    public void saveInfo(@RequestBody MoviePlayingInfoSaveDto moviePlayingInfoSaveDto) {
        moviePlayingInfoService.save(moviePlayingInfoSaveDto);
    }
}
