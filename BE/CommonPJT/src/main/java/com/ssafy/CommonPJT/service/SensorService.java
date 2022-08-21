package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.Sensor;
import com.ssafy.CommonPJT.dto.Sensor.SensorDto;
import com.ssafy.CommonPJT.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    @Transactional
    public void save(SensorDto sensorDto) {
        if (sensorRepository.findAll().isEmpty()) {
            sensorRepository.save(sensorDto.toEntity());
        } else {
            throw new IllegalArgumentException("이미 값이 존재합니다.");
        }
    }

    @Transactional
    public void edit(SensorDto sensorDto) {
        Long id = 1L;
        Sensor sensor1 = sensorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 값입니다."));
        sensor1.setLengthValue(sensorDto.getLengthValue());
        sensorRepository.save(sensor1);
    }

    public SensorDto getValue() {
        Long id = 1L;
        Sensor sensor1 = sensorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 값입니다."));
        return new SensorDto(sensor1);
    }
}
