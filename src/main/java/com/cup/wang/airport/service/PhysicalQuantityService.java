package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.PhysicalQuantityMapper;
import com.cup.wang.airport.model.unit.PhysicalQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/9 15:45
 */
@Service
public class PhysicalQuantityService {

    @Autowired
    PhysicalQuantityMapper physicalQuantityMapper;

    public List<PhysicalQuantity> getAllPhysicalQuantity() {
        return physicalQuantityMapper.getAllPhysicalQuantity();
    }

    public PhysicalQuantity getPhysicalQuantityById(Integer id) {
        return physicalQuantityMapper.selectByPrimaryKey(id);
    }

    public int updatePhysicalQuantity(PhysicalQuantity physicalQuantity) {
        return physicalQuantityMapper.updateByPrimaryKeySelective(physicalQuantity);
    }

    public int addPhysicalQuantity(PhysicalQuantity physicalQuantity) {
        return physicalQuantityMapper.insertSelective(physicalQuantity);
    }

    public int deletePhysicalQuantity(Integer id) {
        return physicalQuantityMapper.deleteByPrimaryKey(id);
    }

    public List<PhysicalQuantity> getAllPhysicalQuantitySimple() {
        return physicalQuantityMapper.getAllPhysicalQuantitySimple();
    }

    public PhysicalQuantity getSimplePhysicalQuantityById(Integer id) {
        return physicalQuantityMapper.getSimplePhysicalQuantityById(id);
    }
}
