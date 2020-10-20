package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityRelativeMapper;
import com.cup.wang.airport.model.density.DensityRelative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:10
 */
@Service
public class DensityRelativeService {

    @Autowired
    DensityRelativeMapper densityRelativeMapper;

    public List<DensityRelative> getAllDensityRelatives() {
        return densityRelativeMapper.getAllDensityRelatives();
    }

    public DensityRelative getDensityRelativeById(Integer id) {
        return densityRelativeMapper.getDensityRelativeById(id);
    }

    public Integer updateDensityRelative(DensityRelative densityRelative) {
        return densityRelativeMapper.updateDensityRelative(densityRelative);
    }

    public Integer addDensityRelative(DensityRelative densityRelative) {
        return densityRelativeMapper.addDensityRelative(densityRelative);
    }

    public Integer deleteDensityRelativeById(Integer id) {
        return densityRelativeMapper.deleteDensityRelativeById(id);
    }
}

