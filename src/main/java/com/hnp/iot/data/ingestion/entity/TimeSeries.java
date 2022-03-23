package com.hnp.iot.data.ingestion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity(name = "time_series")
public class TimeSeries {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "sensor_id")
    private String sensorId;

    @Column(name = "wind_speed_mph")
    private float windSpeed;

    @Column(name = "wind_direction_degrees")
    private float windDirection;

    @Column(name = "methane_concentration_ppm")
    private float methaneConcentration;

    @Column(name = "reading_time_stamp")
    @JsonFormat(timezone = "UTC", pattern = "yyyy-MMM-dd HH:mm:ss")
    private LocalDateTime readingTimeStamp;
}
