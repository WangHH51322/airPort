package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ViscosityFittingMapper;
import com.cup.wang.airport.model.viscosity.ViscosityFitting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/26 10:53
 */
@Service
public class ViscosityFittingService {

    @Autowired
    ViscosityFittingMapper viscosityFittingMapper;

    public List<ViscosityFitting> getAllViscosityFittings() {
        return viscosityFittingMapper.getAllViscosityFittings();
    }

    public List<ViscosityFitting> getViscosityFittingsByViscosityId(Integer did) {
        return viscosityFittingMapper.getViscosityFittingsByViscosityId(did);
    }

    public int updateViscosityFittings(List<ViscosityFitting> viscosityFittings) {
        return viscosityFittingMapper.updateViscosityFittings(viscosityFittings);
    }


    public int addViscosityFittingsByViscosityId(List<ViscosityFitting> viscosityFittings) {
        return viscosityFittingMapper.addViscosityFittingsByViscosityId(viscosityFittings);
    }
}
