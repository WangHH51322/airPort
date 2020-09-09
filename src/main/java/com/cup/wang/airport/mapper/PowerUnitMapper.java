package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.PowerUnit;

import java.util.List;

public interface PowerUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PowerUnit record);

    int insertSelective(PowerUnit record);

    PowerUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PowerUnit record);

    int updateByPrimaryKey(PowerUnit record);

    List<PowerUnit> getAllPowerUnits();
}