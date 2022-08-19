package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.dto.Sensor.SensorDto;
import com.ssafy.CommonPJT.service.SensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/sensor")
@Api("SensorController")
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    @ApiOperation(value = "기존값 조회")
    public ResponseEntity<SensorDto> getValue() {
        return new ResponseEntity<>(sensorService.getValue(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "새로운 수치 저장")
    public void setValue(SensorDto sensorDto) {
        sensorService.save(sensorDto);
    }

    @PutMapping
    @ApiOperation(value = "새로운 수치 갱신")
    public void putValue(SensorDto sensorDto) {
        sensorService.edit(sensorDto);
    }
}
