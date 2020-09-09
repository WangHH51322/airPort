package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.HeatValueMassUnit;

import java.util.List;

public interface HeatValueMassUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatValueMassUnit record);

    int insertSelective(HeatValueMassUnit record);

    HeatValueMassUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatValueMassUnit record);

    int updateByPrimaryKey(HeatValueMassUnit record);

    List<HeatValueMassUnit> getAllHeatValueMassUnits();
}