package com.cup.wang.airport.model.viscosity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViscosityFitting implements Serializable {
    private Integer id;

    private Integer viscosityId;

    private Double viscosity;

    private Double temperature;
}