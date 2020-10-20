package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.settingsData.SettingsData;

import java.util.List;

public interface SettingsDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettingsData record);

    int insertSelective(SettingsData record);

    SettingsData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettingsData record);

    int updateByPrimaryKey(SettingsData record);

    List<SettingsData> getAll();
}