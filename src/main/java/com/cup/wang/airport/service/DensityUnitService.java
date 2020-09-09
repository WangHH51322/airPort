package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityUnitMapper;
import com.cup.wang.airport.model.DensityUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:12
 */
@Service
public class DensityUnitService {

    @Autowired
    DensityUnitMapper densityUnitMapper;

    public List<DensityUnit> getAllDensityUnit() {
        return densityUnitMapper.getAllDensityUnit();
    }

    public Integer updateDensityUnit(DensityUnit densityUnit) {
        return densityUnitMapper.updateByPrimaryKeySelective(densityUnit);
    }

    public Integer addDensityUnit(DensityUnit densityUnit) {
        return densityUnitMapper.insertSelective(densityUnit);
    }

    public Integer deleteDensityUnitById(Integer id) {
        return densityUnitMapper.deleteByPrimaryKey(id);
    }

    public Integer addDensityUnitAndReturnId(DensityUnit densityUnit) {
        return densityUnitMapper.addDensityUnitAndReturnId(densityUnit);
    }


    public DensityUnit getDensityUnitById(Integer id) {
        return densityUnitMapper.getDensityUnitById(id);
    }
}
