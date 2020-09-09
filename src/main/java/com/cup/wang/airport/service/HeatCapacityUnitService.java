package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeatCapacityUnitMapper;
import com.cup.wang.airport.model.HeatCapacityUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:03
 */
@Service
public class HeatCapacityUnitService {

    @Autowired
    HeatCapacityUnitMapper heatCapacityUnitMapper;

    public List<HeatCapacityUnit> getAllHeatCapacityUnits() {
        return heatCapacityUnitMapper.getAllHeatCapacityUnits();
    }

    public HeatCapacityUnit getHeatCapacityUnitById(Integer id) {
        return heatCapacityUnitMapper.selectByPrimaryKey(id);
    }

    public int addHeatCapacityUnit(HeatCapacityUnit heatCapacityUnit) {
        return heatCapacityUnitMapper.insertSelective(heatCapacityUnit);
    }

    public int updateHeatCapacityUnit(HeatCapacityUnit heatCapacityUnit) {
        return heatCapacityUnitMapper.updateByPrimaryKeySelective(heatCapacityUnit);
    }

    public int deleteHeatCapacityUnitById(Integer id) {
        return heatCapacityUnitMapper.deleteByPrimaryKey(id);
    }
}
