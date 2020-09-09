package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityMapper;
import com.cup.wang.airport.model.Density;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:59
 */
@Service
public class DensityService {

    @Autowired
    DensityMapper densityMapper;

    public List<Density> getAllDensities() {
        return densityMapper.getAllDensities();
    }

    public Density getDensityById(Integer id) {
        return densityMapper.getDensityById(id);
    }
}
