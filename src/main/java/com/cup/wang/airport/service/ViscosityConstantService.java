package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ViscosityConstantMapper;
import com.cup.wang.airport.model.viscosity.ViscosityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/26 10:12
 */
@Service
public class ViscosityConstantService {

    @Autowired
    ViscosityConstantMapper viscosityConstantMapper;

    public List<ViscosityConstant> getAllViscosityConstants() {
        return viscosityConstantMapper.getAllViscosityConstants();
    }


    public ViscosityConstant getViscosityConstantByViscosityId(Integer did) {
        return viscosityConstantMapper.getViscosityConstantByDensityId(did);
    }

    public int updateViscosityConstant(ViscosityConstant viscosityConstant) {
        return viscosityConstantMapper.updateByPrimaryKeySelective(viscosityConstant);
    }

    public int addViscosityConstantByDensityId(ViscosityConstant viscosityConstant) {
        return viscosityConstantMapper.insertSelective(viscosityConstant);
    }

    public int deleteViscosityConstantById(Integer id) {
        return viscosityConstantMapper.deleteByPrimaryKey(id);
    }
}
