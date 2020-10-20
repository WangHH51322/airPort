package com.cup.wang.airport.model.viscosity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViscosityConstant implements Serializable {
    private Integer id;

    private Double value;

    private Integer viscosityId;
}