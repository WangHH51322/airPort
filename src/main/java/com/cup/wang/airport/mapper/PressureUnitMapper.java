package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.PressureUnit;

import java.util.List;

public interface PressureUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PressureUnit record);

    int insertSelective(PressureUnit record);

    PressureUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PressureUnit record);

    int updateByPrimaryKey(PressureUnit record);

    List<PressureUnit> getAllPressureUnits();
}