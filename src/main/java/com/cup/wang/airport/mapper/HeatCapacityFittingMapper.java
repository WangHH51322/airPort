package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.heatCapacity.HeatCapacityFitting;

import java.util.List;

public interface HeatCapacityFittingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatCapacityFitting record);

    int insertSelective(HeatCapacityFitting record);

    HeatCapacityFitting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatCapacityFitting record);

    int updateByPrimaryKey(HeatCapacityFitting record);

    List<HeatCapacityFitting> getAllHeatCapacityFittings();

    List<HeatCapacityFitting> getHeatCapacityFittingsByHCId(Integer did);

    int updateHeatCapacityFittings(List<HeatCapacityFitting> heatCapacityFittings);

    int addHeatCapacityFittingsByHCId(List<HeatCapacityFitting> heatCapacityFittings);
}