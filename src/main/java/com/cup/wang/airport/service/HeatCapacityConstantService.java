package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeatCapacityConstantMapper;
import com.cup.wang.airport.model.heatCapacity.HeatCapacityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 21:01
 */
@Service
public class HeatCapacityConstantService {

    @Autowired
    HeatCapacityConstantMapper heatCapacityConstantMapper;

    public List<HeatCapacityConstant> getAllHeatCapacityConstants() {
        return heatCapacityConstantMapper.getAllHeatCapacityConstants();
    }

    public HeatCapacityConstant getHeatCapacityConstantByHCId(Integer did) {
        return heatCapacityConstantMapper.getHeatCapacityConstantByHCId(did);
    }

    public int updateHeatCapacityConstant(HeatCapacityConstant heatCapacityConstant) {
        return heatCapacityConstantMapper.updateByPrimaryKeySelective(heatCapacityConstant);
    }

    public int addHeatCapacityConstantByHCId(HeatCapacityConstant heatCapacityConstant) {
        return heatCapacityConstantMapper.insertSelective(heatCapacityConstant);
    }

    public int deleteHeatCapacityConstantById(Integer id) {
        return heatCapacityConstantMapper.deleteByPrimaryKey(id);
    }
}
