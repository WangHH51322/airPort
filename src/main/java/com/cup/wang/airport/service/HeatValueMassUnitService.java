package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeatValueMassUnitMapper;
import com.cup.wang.airport.model.HeatValueMassUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:43
 */
@Service
public class HeatValueMassUnitService {

    @Autowired
    HeatValueMassUnitMapper heatValueMassUnitMapper;

    public List<HeatValueMassUnit> getAllHeatValueMassUnits() {
        return heatValueMassUnitMapper.getAllHeatValueMassUnits();
    }

    public HeatValueMassUnit getHeatValueMassUnitById(Integer id) {
        return heatValueMassUnitMapper.selectByPrimaryKey(id);
    }

    public int addHeatValueMassUnit(HeatValueMassUnit heatValueMassUnit) {
        return heatValueMassUnitMapper.insertSelective(heatValueMassUnit);
    }

    public int updateHeatValueMassUnit(HeatValueMassUnit heatValueMassUnit) {
        return heatValueMassUnitMapper.updateByPrimaryKeySelective(heatValueMassUnit);
    }

    public int deleteHeatValueMassUnitById(Integer id) {
        return heatValueMassUnitMapper.deleteByPrimaryKey(id);
    }
}
