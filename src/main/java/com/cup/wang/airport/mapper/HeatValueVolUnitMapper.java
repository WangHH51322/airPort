package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.HeatValueVolUnit;

import java.util.List;

public interface HeatValueVolUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatValueVolUnit record);

    int insertSelective(HeatValueVolUnit record);

    HeatValueVolUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatValueVolUnit record);

    int updateByPrimaryKey(HeatValueVolUnit record);

    List<HeatValueVolUnit> getAllHeatValueVolUnits();
}