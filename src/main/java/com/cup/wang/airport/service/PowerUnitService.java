package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.PowerUnitMapper;
import com.cup.wang.airport.model.PowerUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:06
 */
@Service
public class PowerUnitService {

    @Autowired
    PowerUnitMapper powerUnitMapper;

    public List<PowerUnit> getAllPowerUnits() {
        return powerUnitMapper.getAllPowerUnits();
    }

    public PowerUnit getPowerUnitById(Integer id) {
        return powerUnitMapper.selectByPrimaryKey(id);
    }

    public int addPowerUnit(PowerUnit powerUnit) {
        return powerUnitMapper.insertSelective(powerUnit);
    }

    public int updatePowerUnit(PowerUnit powerUnit) {
        return powerUnitMapper.updateByPrimaryKeySelective(powerUnit);
    }

    public int deletePowerUnitById(Integer id) {
        return powerUnitMapper.deleteByPrimaryKey(id);
    }
}
