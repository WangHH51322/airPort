package com.cup.wang.airport.model.heatCapacity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeatCapacity implements Serializable {
    private Integer id;
    private Integer heatCapacityUnitId;
    private Integer heatCapacityValueId;
    private Integer liquidId;

    private HeatCapacityConstant heatCapacityConstant;
    private List<HeatCapacityFitting> heatCapacityFitting;
}