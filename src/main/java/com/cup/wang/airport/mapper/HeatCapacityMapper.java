package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.heatCapacity.HeatCapacity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeatCapacityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatCapacity record);

    int insertSelective(HeatCapacity record);

    HeatCapacity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatCapacity record);

    int updateByPrimaryKey(HeatCapacity record);

    List<HeatCapacity> getHeatCapacityByLiquidId(@Param("id") Integer id, @Param("heatCapacityValueId") Integer heatCapacityValueId);
}