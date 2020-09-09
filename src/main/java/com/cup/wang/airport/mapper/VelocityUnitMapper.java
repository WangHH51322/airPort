package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.VelocityUnit;

import java.util.List;

public interface VelocityUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VelocityUnit record);

    int insertSelective(VelocityUnit record);

    VelocityUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VelocityUnit record);

    int updateByPrimaryKey(VelocityUnit record);

    List<VelocityUnit> getAllVelocityUnits();
}