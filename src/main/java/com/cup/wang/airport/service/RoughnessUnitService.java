package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.RoughnessUnitMapper;
import com.cup.wang.airport.model.RoughnessUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:53
 */
@Service
public class RoughnessUnitService {

    @Autowired
    RoughnessUnitMapper roughnessUnitMapper;

    public List<RoughnessUnit> getAllRoughnessUnits() {
        return roughnessUnitMapper.getAllRoughnessUnits();
    }

    public RoughnessUnit getRoughnessUnitById(Integer id) {
        return roughnessUnitMapper.selectByPrimaryKey(id);
    }

    public int addRoughnessUnit(RoughnessUnit roughnessUnit) {
        return roughnessUnitMapper.insertSelective(roughnessUnit);
    }

    public int updateRoughnessUnit(RoughnessUnit roughnessUnit) {
        return roughnessUnitMapper.updateByPrimaryKeySelective(roughnessUnit);
    }

    public int deleteRoughnessUnitById(Integer id) {
        return roughnessUnitMapper.deleteByPrimaryKey(id);
    }
}
