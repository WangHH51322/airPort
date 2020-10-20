package com.cup.wang.airport.model.heatCapacity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeatCapacityConstant implements Serializable {
    private Integer id;

    private Double value;

    private Integer heatCapacityId;
}