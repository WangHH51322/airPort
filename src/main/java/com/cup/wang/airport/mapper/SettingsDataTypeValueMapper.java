package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.SettingsDataTypeValue;

public interface SettingsDataTypeValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettingsDataTypeValue record);

    int insertSelective(SettingsDataTypeValue record);

    SettingsDataTypeValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettingsDataTypeValue record);

    int updateByPrimaryKey(SettingsDataTypeValue record);
}