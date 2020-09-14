package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.QuantityUnitMapper;
import com.cup.wang.airport.model.QuantityUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/9 16:15
 */
@Service
public class QuantityUnitService {

    @Autowired
    QuantityUnitMapper quantityUnitMapper;

    public List<QuantityUnit> getAllQuantityUnit() {
        return quantityUnitMapper.getAllQuantityUnit();
    }

    public QuantityUnit getQuantityUnitById(Integer id) {
        return quantityUnitMapper.selectByPrimaryKey(id);
    }

    public int updateQuantityUnit(QuantityUnit quantityUnit) {
        return quantityUnitMapper.updateByPrimaryKeySelective(quantityUnit);
    }

    public int addQuantityUnit(QuantityUnit quantityUnit) {
        return quantityUnitMapper.insertSelective(quantityUnit);
    }

    public int deleteQuantityUnit(Integer id) {
        return quantityUnitMapper.deleteByPrimaryKey(id);
    }

    public List<QuantityUnit> getQuantityUnitByPhysicalQuantityId(Integer id) {
        return quantityUnitMapper.getQuantityUnitByPhysicalQuantityId(id);
    }

    public List<QuantityUnit> getQuantityUnitByPhysicalQuantityIdAndUnitSystemId(Integer id1, Integer id2) {
        return quantityUnitMapper.getQuantityUnitByPhysicalQuantityIdAndUnitSystemId(id1,id2);
    }

    public List<QuantityUnit> getQuantityUnitByUnitSystemId(Integer id) {
        return quantityUnitMapper.getQuantityUnitByUnitSystemId(id);
    }
}
