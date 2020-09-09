package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ViscosityKinematicUnitMapper;
import com.cup.wang.airport.model.ViscosityKinematicUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:36
 */
@Service
public class ViscosityKinematicUnitService {

    @Autowired
    ViscosityKinematicUnitMapper viscosityKinematicUnitMapper;

    public List<ViscosityKinematicUnit> getAllHeadUnitViscosityKinematicUnits() {
        return viscosityKinematicUnitMapper.getAllHeadUnitViscosityKinematicUnits();
    }

    public ViscosityKinematicUnit getViscosityKinematicUnitById(Integer id) {
        return viscosityKinematicUnitMapper.selectByPrimaryKey(id);
    }

    public int addViscosityKinematicUnit(ViscosityKinematicUnit viscosityKinematicUnit) {
        return viscosityKinematicUnitMapper.insertSelective(viscosityKinematicUnit);
    }

    public int updateViscosityKinematicUnit(ViscosityKinematicUnit viscosityKinematicUnit) {
        return viscosityKinematicUnitMapper.updateByPrimaryKeySelective(viscosityKinematicUnit);
    }

    public int deleteViscosityKinematicUnitById(Integer id) {
        return viscosityKinematicUnitMapper.deleteByPrimaryKey(id);
    }
}
