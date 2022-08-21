package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoResDto;
import com.ssafy.CommonPJT.dto.MoviePlayingInfo.MoviePlayingInfoSaveDto;
import com.ssafy.CommonPJT.service.MoviePlayingInfoService;
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
@Api("MoviePlayingInfoController")
@RequestMapping("/api/info")
public class MoviePlayingInfoController {

    private final MoviePlayingInfoService moviePlayingInfoService;

    @ApiOperation(value = "전체 영화 상영 정보", notes = "전체 영화 상영 정보를 출력한다.")
    @GetMapping
    public ResponseEntity<List<MoviePlayingInfoResDto>> getInfoList() {
        log.info("영화 상영 정보 리스트를 조회합니다.");
        return new ResponseEntity<>(moviePlayingInfoService.findInfo(), HttpStatus.OK);
    }

    @ApiOperation(value = "영화 상영 정보", notes = "영화 상영 정보를 출력한다.")
    @GetMapping("/{movieplayinginfoId}")
    public ResponseEntity<MoviePlayingInfoResDto> getInfo(@PathVariable Long movieplayinginfoId) {
        log.info("영화 상영 정보를 조회합니다.");
        return new ResponseEntity<>(moviePlayingInfoService.findById(movieplayinginfoId), HttpStatus.OK);
    }


    @ApiOperation(value = "영화 상영 정보 저장", notes = "영화 상영 정보를 저장한다.")
    @PostMapping
    public void saveInfo(@RequestBody MoviePlayingInfoSaveDto moviePlayingInfoSaveDto) {
        moviePlayingInfoService.save(moviePlayingInfoSaveDto);
        log.info("영화 상영 정보를 저장합니다.");
    }

    @ApiOperation(value = "영화 상영 정보 삭제", notes = "영화 상영 정보를 삭제합니다.")
    @DeleteMapping("/{movieInfoId}")
    public void deleteInfoById(@PathVariable Long movieInfoId) {
        moviePlayingInfoService.deleteById(movieInfoId);
    }
}
