package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.FlowRateMassUnitMapper;
import com.cup.wang.airport.model.FlowRateMassUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 9:54
 */
@Service
public class FlowRateMassUnitService {

    @Autowired
    FlowRateMassUnitMapper flowRateMassUnitMapper;

    public List<FlowRateMassUnit> getAllFlowRateMassUnit() {
        return flowRateMassUnitMapper.findAll();
    }

    public Optional<FlowRateMassUnit> getFlowRateMassUnitById(Integer id) {
        return flowRateMassUnitMapper.findById(id);
    }

    public FlowRateMassUnit updateFlowRateMassUnit(FlowRateMassUnit flowRateMassUnit) {
        return flowRateMassUnitMapper.saveAndFlush(flowRateMassUnit);
    }

    public FlowRateMassUnit addFlowRateMassUnit(FlowRateMassUnit flowRateMassUnit) {
        return flowRateMassUnitMapper.saveAndFlush(flowRateMassUnit);
    }

    public void deleteFlowRateMassUnitById(Integer id) {
        flowRateMassUnitMapper.deleteById(id);
    }
}
