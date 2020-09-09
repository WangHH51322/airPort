package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeatValueVolUnitMapper;
import com.cup.wang.airport.model.HeatValueVolUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:56
 */
@Service
public class HeatValueVolUnitService {

    @Autowired
    HeatValueVolUnitMapper heatValueVolUnitMapper;

    public List<HeatValueVolUnit> getAllHeatValueVolUnits() {
        return heatValueVolUnitMapper.getAllHeatValueVolUnits();
    }

    public HeatValueVolUnit getHeatValueVolUnitById(Integer id) {
        return heatValueVolUnitMapper.selectByPrimaryKey(id);
    }

    public int addHeatValueVolUnit(HeatValueVolUnit heatValueVolUnit) {
        return heatValueVolUnitMapper.insertSelective(heatValueVolUnit);
    }

    public int updateHeatValueVolUnit(HeatValueVolUnit heatValueVolUnit) {
        return heatValueVolUnitMapper.updateByPrimaryKeySelective(heatValueVolUnit);
    }

    public int deleteHeatValueVolUnitById(Integer id) {
        return heatValueVolUnitMapper.deleteByPrimaryKey(id);
    }
}
