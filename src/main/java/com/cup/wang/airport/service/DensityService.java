package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityMapper;
import com.cup.wang.airport.model.density.Density;
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

    public int updateDensity(Density density) {
        return densityTestMapper.updateByPrimaryKeySelective(density);
    }

    public int addDensity(Density density) {
        return densityTestMapper.insertSelective(density);
    }

    public int deleteDensityById(Integer id) {
        return densityTestMapper.deleteByPrimaryKey(id);
    }
}
