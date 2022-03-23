create table iot.time_series (
    id SERIAL NOT NULL,
    sensor_id VARCHAR(4) NOT NULL,
    wind_speed_mph FLOAT NOT NULL,
    wind_direction_degrees FLOAT NOT NULL,
    methane_concentration_ppm NUMERIC NOT NULL,
    reading_time_stamp TIMESTAMP NOT NULL,
    CONSTRAINT time_series_pk PRIMARY KEY(id)
);