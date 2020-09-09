package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityTestMapper;
import com.cup.wang.airport.model.DensityTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/3 17:44
 */
@Service
public class DensityTestService {

    @Autowired
    DensityTestMapper densityTestMapper;

    public DensityTest getDensityById(Integer id) {
        Integer densityValueId = densityTestMapper.selectByPrimaryKey(id).getDensityValueId();
        return densityTestMapper.selectByKey(id,densityValueId).get(0);
    }
}
