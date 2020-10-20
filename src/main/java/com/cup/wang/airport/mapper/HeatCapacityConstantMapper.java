package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.heatCapacity.HeatCapacityConstant;

import java.util.List;

public interface HeatCapacityConstantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatCapacityConstant record);

    int insertSelective(HeatCapacityConstant record);

    HeatCapacityConstant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatCapacityConstant record);

    int updateByPrimaryKey(HeatCapacityConstant record);

    List<HeatCapacityConstant> getAllHeatCapacityConstants();

    HeatCapacityConstant getHeatCapacityConstantByHCId(Integer did);
}