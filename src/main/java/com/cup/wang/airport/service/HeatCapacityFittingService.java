package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeatCapacityFittingMapper;
import com.cup.wang.airport.model.heatCapacity.HeatCapacityFitting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 21:17
 */
@Service
public class HeatCapacityFittingService {

    @Autowired
    HeatCapacityFittingMapper heatCapacityFittingMapper;

    public List<HeatCapacityFitting> getAllHeatCapacityFittings() {
        return heatCapacityFittingMapper.getAllHeatCapacityFittings();
    }

    public List<HeatCapacityFitting> getHeatCapacityFittingsByHCId(Integer did) {
        return heatCapacityFittingMapper.getHeatCapacityFittingsByHCId(did);
    }

    public int updateHeatCapacityFittings(List<HeatCapacityFitting> heatCapacityFittings) {
        return heatCapacityFittingMapper.updateHeatCapacityFittings(heatCapacityFittings);
    }

    public int addHeatCapacityFittingsByHCId(List<HeatCapacityFitting> heatCapacityFittings) {
        return heatCapacityFittingMapper.addHeatCapacityFittingsByHCId(heatCapacityFittings);
    }
}
