package com.cup.wang.airport.model.settingsData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDataType {
    private Integer id;

    private String name;

    private Integer dataId;

    private List<SettingsDataTypeValue> typeValue;
}