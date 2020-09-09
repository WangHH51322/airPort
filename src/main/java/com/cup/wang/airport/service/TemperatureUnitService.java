package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.TemperatureUnitMapper;
import com.cup.wang.airport.model.TemperatureUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/22 18:35
 */
@Service
public class TemperatureUnitService {

    @Autowired
    TemperatureUnitMapper temperatureUnitMapper;

    public List<TemperatureUnit> getAllTemperatureUnit() {
        return temperatureUnitMapper.findAll();
    }

    public Optional<TemperatureUnit> getTemperatureUnitById(Integer id) {
        return temperatureUnitMapper.findById(id);
    }

    public TemperatureUnit updateTemperatureUnit(TemperatureUnit temperatureUnit) {
        return temperatureUnitMapper.saveAndFlush(temperatureUnit);
    }

    public TemperatureUnit addTemperatureUnit(TemperatureUnit temperatureUnit) {
        return temperatureUnitMapper.saveAndFlush(temperatureUnit);
    }

    public void deleteTemperatureUnitById(Integer id) {
        temperatureUnitMapper.deleteById(id);
    }
}
