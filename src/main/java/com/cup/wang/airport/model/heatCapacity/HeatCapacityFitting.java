package com.cup.wang.airport.model.heatCapacity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeatCapacityFitting implements Serializable {
    private Integer id;

    private Integer heatCapacityId;

    private Double heatCapacity;

    private Double vapor;

    private Double temperature;
}