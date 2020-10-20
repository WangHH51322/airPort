package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.settingsData.SettingsDataType;

public interface SettingsDataTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettingsDataType record);

    int insertSelective(SettingsDataType record);

    SettingsDataType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettingsDataType record);

    int updateByPrimaryKey(SettingsDataType record);
}