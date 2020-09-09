package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.SettingsDataTypeValueChildren;

public interface SettingsDataTypeValueChildrenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettingsDataTypeValueChildren record);

    int insertSelective(SettingsDataTypeValueChildren record);

    SettingsDataTypeValueChildren selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettingsDataTypeValueChildren record);

    int updateByPrimaryKey(SettingsDataTypeValueChildren record);
}