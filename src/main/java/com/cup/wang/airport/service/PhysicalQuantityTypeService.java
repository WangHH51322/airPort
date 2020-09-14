package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.PhysicalQuantityTypeMapper;
import com.cup.wang.airport.model.PhysicalQuantityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/13 20:39
 */
@Service
public class PhysicalQuantityTypeService {

    @Autowired
    PhysicalQuantityTypeMapper physicalQuantityTypeMapper;

    public List<PhysicalQuantityType> getAllPhysicalQuantityTypes() {
        return physicalQuantityTypeMapper.getAllPhysicalQuantityTypes();
    }
}
