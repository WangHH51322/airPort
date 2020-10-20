package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeatCapacityMapper;
import com.cup.wang.airport.model.heatCapacity.HeatCapacity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 21:38
 */
@Service
public class HeatCapacityService {

    @Autowired
    HeatCapacityMapper heatCapacityMapper;

    public HeatCapacity getHeatCapacityByLiquidId(Integer id) {
        Integer heatCapacityValueId = heatCapacityMapper.selectByPrimaryKey(id).getHeatCapacityValueId();
        return heatCapacityMapper.getHeatCapacityByLiquidId(id,heatCapacityValueId).get(0);
    }

    public int updateHeatCapacity(HeatCapacity heatCapacity) {
        return heatCapacityMapper.updateByPrimaryKeySelective(heatCapacity);
    }

    public int addHeatCapacityByLiquidId(HeatCapacity heatCapacity) {
        return heatCapacityMapper.insertSelective(heatCapacity);
    }
}
