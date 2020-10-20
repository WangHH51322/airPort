package com.cup.wang.airport.model.settingsData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingsData {
    private Integer id;

    private String name;

    private String variableName;

    private SettingsDataType type;

}