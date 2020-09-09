package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.PowerUnitMapper;
import com.cup.wang.airport.mapper.PressureUnitMapper;
import com.cup.wang.airport.model.PowerUnit;
import com.cup.wang.airport.model.PressureUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:30
 */
@Service
public class PressureUnitService {

    @Autowired
    PressureUnitMapper pressureUnitMapper;

    public List<PressureUnit> getAllPressureUnits() {
        return pressureUnitMapper.getAllPressureUnits();
    }

    public PressureUnit getPressureUnitById(Integer id) {
        return pressureUnitMapper.selectByPrimaryKey(id);
    }

    public int addPressureUnit(PressureUnit pressureUnit) {
        return pressureUnitMapper.insertSelective(pressureUnit);
    }

    public int updatePressureUnit(PressureUnit pressureUnit) {
        return pressureUnitMapper.updateByPrimaryKeySelective(pressureUnit);
    }

    public int deletePressureUnitById(Integer id) {
        return pressureUnitMapper.deleteByPrimaryKey(id);
    }
}
