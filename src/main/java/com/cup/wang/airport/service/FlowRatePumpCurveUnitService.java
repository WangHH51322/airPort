package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.FlowRatePumpCurveUnitMapper;
import com.cup.wang.airport.model.FlowRatePumpCurveUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 15:20
 */
@Service
public class FlowRatePumpCurveUnitService {

    @Autowired
    FlowRatePumpCurveUnitMapper flowRatePumpCurveUnitMapper;

    public List<FlowRatePumpCurveUnit> getAllFlowRatePumpCurveUnit() {
        return flowRatePumpCurveUnitMapper.findAll();
    }

    public Optional<FlowRatePumpCurveUnit> getFlowRatePumpCurveUnitById(Integer id) {
        return flowRatePumpCurveUnitMapper.findById(id);
    }

    public FlowRatePumpCurveUnit updateFlowRatePumpCurveUnit(FlowRatePumpCurveUnit flowRatePumpCurveUnit) {
        return flowRatePumpCurveUnitMapper.saveAndFlush(flowRatePumpCurveUnit);
    }

    public FlowRatePumpCurveUnit addFlowRatePumpCurveUnit(FlowRatePumpCurveUnit flowRatePumpCurveUnit) {
        return flowRatePumpCurveUnitMapper.saveAndFlush(flowRatePumpCurveUnit);
    }

    public void deleteFlowRatePumpCurveUnitById(Integer id) {
        flowRatePumpCurveUnitMapper.deleteById(id);
    }
}
