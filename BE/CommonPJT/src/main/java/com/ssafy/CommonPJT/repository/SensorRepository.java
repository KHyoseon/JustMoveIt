package com.ssafy.CommonPJT.repository;

import com.ssafy.CommonPJT.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
