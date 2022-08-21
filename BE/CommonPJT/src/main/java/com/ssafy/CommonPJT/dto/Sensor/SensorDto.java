package com.ssafy.CommonPJT.dto.Sensor;

import com.ssafy.CommonPJT.domain.Sensor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

    String lengthValue;

    public SensorDto(Sensor sensor) {
        this.lengthValue = sensor.getLengthValue();
    }

    @Builder
    public Sensor toEntity() {
        return Sensor.builder()
                .lengthValue(this.lengthValue)
                .build();
    }
}
