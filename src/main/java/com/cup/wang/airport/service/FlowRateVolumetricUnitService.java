package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.FlowRateVolumetricUnitMapper;
import com.cup.wang.airport.model.FlowRateVolumetricUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 15:43
 */
@Service
public class FlowRateVolumetricUnitService {

    @Autowired
    FlowRateVolumetricUnitMapper flowRateVolumetricUnitMapper;

    public List<FlowRateVolumetricUnit> getAllFlowRateVolumetricUnit() {
        return flowRateVolumetricUnitMapper.findAll();
    }

    public Optional<FlowRateVolumetricUnit> getFlowRateVolumetricUnitById(Integer id) {
        return flowRateVolumetricUnitMapper.findById(id);
    }

    public FlowRateVolumetricUnit updateFlowRateVolumetricUnit(FlowRateVolumetricUnit flowRateVolumetricUnit) {
        return flowRateVolumetricUnitMapper.saveAndFlush(flowRateVolumetricUnit);
    }

    public FlowRateVolumetricUnit addFlowRateVolumetricUnit(FlowRateVolumetricUnit flowRateVolumetricUnit) {
        return flowRateVolumetricUnitMapper.saveAndFlush(flowRateVolumetricUnit);
    }

    public void deleteFlowRateVolumetricUnitById(Integer id) {
        flowRateVolumetricUnitMapper.deleteById(id);
    }
}
