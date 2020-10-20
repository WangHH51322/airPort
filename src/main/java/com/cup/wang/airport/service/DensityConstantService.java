package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityConstantMapper;
import com.cup.wang.airport.model.density.DensityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/21 15:36
 */
@Service
public class DensityConstantService {

    @Autowired
    DensityConstantMapper densityConstantMapper;

    public List<DensityConstant> getAllDensityConstants() {
        return densityConstantMapper.getAllDensityConstants();
    }

    public DensityConstant getDensityConstantByDensityId(Integer did) {
        return densityConstantMapper.getDensityConstantByDensityId(did);
    }

    public int updateDensityConstant(DensityConstant densityConstant) {
        return densityConstantMapper.updateByPrimaryKeySelective(densityConstant);
    }

    public int addDensityConstantByDensityId(DensityConstant densityConstant) {
        return densityConstantMapper.insertSelective(densityConstant);
    }
}
