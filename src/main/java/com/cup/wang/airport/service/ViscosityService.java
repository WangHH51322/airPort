package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ViscosityMapper;
import com.cup.wang.airport.model.viscosity.Viscosity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 10:15
 */
@Service
public class ViscosityService {

    @Autowired
    ViscosityMapper viscosityMapper;

    public Viscosity getViscosityByLiquidId(Integer id) {
        Integer viscosityValueId = viscosityMapper.selectByPrimaryKey(id).getViscosityValueId();
        return viscosityMapper.getViscosityByLiquidId(id,viscosityValueId).get(0);
    }

    public int updateViscosity(Viscosity viscosity) {
        return viscosityMapper.updateByPrimaryKeySelective(viscosity);
    }
}
