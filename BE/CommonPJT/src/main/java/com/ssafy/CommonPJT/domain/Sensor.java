package com.ssafy.CommonPJT.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sensor {

    @Id
    @Column(name = "sensor_id")
    Long id;

    @Column
    String lengthValue;

    public void setLengthValue(String lengthValue) {
        this.lengthValue = lengthValue;
    }
}
