package com.cup.wang.airport.model.viscosity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Viscosity implements Serializable {
    private Integer id;
    private Integer viscosityUnitId;
    private Integer viscosityValueId;
    private Integer liquidId;

    private ViscosityConstant viscosityConstant;
    private List<ViscosityFitting> viscosityFittings;
}