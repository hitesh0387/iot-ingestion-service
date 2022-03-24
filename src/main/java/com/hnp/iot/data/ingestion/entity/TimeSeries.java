package com.hnp.iot.data.ingestion.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "time_series", schema = "iot")
public class TimeSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sensor_id")
    private String sensorId;

    @Column(name = "wind_speed_mph")
    private float windSpeed;

    @Column(name = "wind_direction_degrees")
    private float windDirection;

    @Column(name = "methane_concentration_ppm")
    private float methaneConcentration;

    @Column(name = "reading_time_stamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "UTC", pattern = "yyyy-MMM-dd HH:mm:ss")
    private LocalDateTime readingTimeStamp;
}
