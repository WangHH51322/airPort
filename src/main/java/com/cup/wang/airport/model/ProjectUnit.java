package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUnit {
    private Integer id;

    private Integer projectId;

    private Integer densityUnitId;

    private Integer viscosityKinematicUnitId;

    private Integer viscosityDynamicUnitId;

    private Integer lengthUnitId;

    private Integer diameterUnitId;

    private Integer wallThicknessUnitId;

    private Integer roughnessUnitId;

    private DensityUnit densityUnit;

    private ViscosityKinematicUnit viscosityKinematicUnit;

    private ViscosityDynamicUnit viscosityDynamicUnit;
}