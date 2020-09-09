package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DensityTest implements Serializable {

    private Integer id;
    private Integer densityUnitId;
    private Integer densityValueId;
    private Integer liquidId;

    private DensityUnit densityUnit;
    private DensityConstant densityConstant;
    private List<DensityFitting> densityFittingList;
    private DensityPolynomial densityPolynomial;
    private DensityRelative densityRelative;

}