package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.HeatCapacityUnit;

import java.util.List;

public interface HeatCapacityUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatCapacityUnit record);

    int insertSelective(HeatCapacityUnit record);

    HeatCapacityUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatCapacityUnit record);

    int updateByPrimaryKey(HeatCapacityUnit record);

    List<HeatCapacityUnit> getAllHeatCapacityUnits();
}