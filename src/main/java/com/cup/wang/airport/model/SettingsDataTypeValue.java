package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDataTypeValue {
    private Integer id;

    private String value;

    private String label;

    private List<SettingsDataTypeValueChildren> children;

    private Integer typeId;
}