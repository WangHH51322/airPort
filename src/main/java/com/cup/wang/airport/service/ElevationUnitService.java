package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ElevationUnitMapper;
import com.cup.wang.airport.model.ElevationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:27
 */
@Service
public class ElevationUnitService {

    @Autowired
    ElevationUnitMapper elevationUnitMapper;

    public List<ElevationUnit> getAllElevationUnit() {
        return elevationUnitMapper.findAll();
    }

    public Optional<ElevationUnit> getElevationUnitById(Integer id) {
        return elevationUnitMapper.findById(id);
    }

    public ElevationUnit updateElevationUnit(ElevationUnit elevationUnit) {
        return elevationUnitMapper.saveAndFlush(elevationUnit);
    }

    public ElevationUnit addElevationUnit(ElevationUnit elevationUnit) {
        return elevationUnitMapper.saveAndFlush(elevationUnit);
    }

    public void deleteElevationUnitById(Integer id) {
        elevationUnitMapper.deleteById(id);
    }
}
