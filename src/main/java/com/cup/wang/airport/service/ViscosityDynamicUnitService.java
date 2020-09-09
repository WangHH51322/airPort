package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ViscosityDynamicUnitMapper;
import com.cup.wang.airport.model.ViscosityDynamicUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:26
 */
@Service
public class ViscosityDynamicUnitService {

    @Autowired
    ViscosityDynamicUnitMapper viscosityDynamicUnitMapper;

    public List<ViscosityDynamicUnit> getAllViscosityDynamicUnits() {
        return viscosityDynamicUnitMapper.getAllViscosityDynamicUnits();
    }

    public ViscosityDynamicUnit getViscosityDynamicUnitById(Integer id) {
        return viscosityDynamicUnitMapper.selectByPrimaryKey(id);
    }

    public int addViscosityDynamicUnit(ViscosityDynamicUnit viscosityDynamicUnit) {
        return viscosityDynamicUnitMapper.insertSelective(viscosityDynamicUnit);
    }

    public int updateViscosityDynamicUnit(ViscosityDynamicUnit viscosityDynamicUnit) {
        return viscosityDynamicUnitMapper.updateByPrimaryKeySelective(viscosityDynamicUnit);
    }

    public int deleteViscosityDynamicUnitById(Integer id) {
        return viscosityDynamicUnitMapper.deleteByPrimaryKey(id);
    }
}
