package com.cup.wang.airport.model.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUnit {
    private Integer id;

    private String projectVariable;

    private String variableName;

    private Integer variableUnitId;

    private Integer variableUnitType;

    private Integer projectId;

}