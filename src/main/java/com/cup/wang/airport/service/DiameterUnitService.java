package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DiameterUnitMapper;
import com.cup.wang.airport.model.DiameterUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:07
 */
@Service
public class DiameterUnitService {

    @Autowired
    DiameterUnitMapper diameterUnitMapper;

    public List<DiameterUnit> getAllDiameterUnit() {
        return diameterUnitMapper.findAll();
    }

    public Optional<DiameterUnit> getTemperatureUnitById(Integer id) {
        return diameterUnitMapper.findById(id);
    }

    public DiameterUnit updateDiameterUnit(DiameterUnit diameterUnit) {
        return diameterUnitMapper.saveAndFlush(diameterUnit);
    }

    public DiameterUnit addDiameterUnit(DiameterUnit diameterUnit) {
        return diameterUnitMapper.saveAndFlush(diameterUnit);
    }

    public void deleteDiameterUnitById(Integer id) {
        diameterUnitMapper.deleteById(id);
    }
}
