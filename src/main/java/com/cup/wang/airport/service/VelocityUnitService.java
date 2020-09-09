package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.VelocityUnitMapper;
import com.cup.wang.airport.model.VelocityUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:16
 */
@Service
public class VelocityUnitService {

    @Autowired
    VelocityUnitMapper velocityUnitMapper;

    public List<VelocityUnit> getAllVelocityUnits() {
        return velocityUnitMapper.getAllVelocityUnits();
    }

    public VelocityUnit getVelocityUnitById(Integer id) {
        return velocityUnitMapper.selectByPrimaryKey(id);
    }

    public int addVelocityUnit(VelocityUnit velocityUnit) {
        return velocityUnitMapper.insertSelective(velocityUnit);
    }

    public int updateVelocityUnit(VelocityUnit velocityUnit) {
        return velocityUnitMapper.updateByPrimaryKeySelective(velocityUnit);
    }

    public int deleteVelocityUnitById(Integer id) {
        return velocityUnitMapper.deleteByPrimaryKey(id);
    }
}
