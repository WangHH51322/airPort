package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.LengthUnitMapper;
import com.cup.wang.airport.model.LengthUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:43
 */
@Service
public class LengthUnitService {

    @Autowired
    LengthUnitMapper lengthUnitMapper;

    public List<LengthUnit> getAllLengthUnit() {
        return lengthUnitMapper.findAll();
    }

    public Optional<LengthUnit> getLengthUnitById(Integer id) {
        return lengthUnitMapper.findById(id);
    }

    public LengthUnit updateLengthUnit(LengthUnit lengthUnit) {
        return lengthUnitMapper.saveAndFlush(lengthUnit);
    }

    public LengthUnit addLengthUnit(LengthUnit lengthUnit) {
        return lengthUnitMapper.saveAndFlush(lengthUnit);
    }

    public void deleteLengthUnitById(Integer id) {
        lengthUnitMapper.deleteById(id);
    }
}
