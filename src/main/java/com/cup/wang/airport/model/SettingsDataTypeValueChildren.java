package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDataTypeValueChildren {
    private Integer id;

    private String name;

    private String variableName;

    private String type;

    private Integer valueId;

}