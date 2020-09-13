package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityMapper;
import com.cup.wang.airport.model.Density;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/3 17:44
 */
@Service
public class DensityService {

    @Autowired
    DensityMapper densityTestMapper;

    public Density getDensityById(Integer id) {
        Integer densityValueId = densityTestMapper.selectByPrimaryKey(id).getDensityValueId();
        return densityTestMapper.selectByKey(id,densityValueId).get(0);
    }
}
