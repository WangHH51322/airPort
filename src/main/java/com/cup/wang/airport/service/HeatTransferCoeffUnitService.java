package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeatTransferCoeffUnitMapper;
import com.cup.wang.airport.model.HeatTransferCoeffUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:28
 */
@Service
public class HeatTransferCoeffUnitService {

    @Autowired
    HeatTransferCoeffUnitMapper heatTransferCoeffUnitMapper;

    public List<HeatTransferCoeffUnit> getAllHeatTransferCoeffUnits() {
        return heatTransferCoeffUnitMapper.getAllHeatTransferCoeffUnits();
    }

    public HeatTransferCoeffUnit getHeatTransferCoeffUnitById(Integer id) {
        return heatTransferCoeffUnitMapper.selectByPrimaryKey(id);
    }

    public int addHeaddHeatTransferCoeffUnit(HeatTransferCoeffUnit heatTransferCoeffUnit) {
        return heatTransferCoeffUnitMapper.insertSelective(heatTransferCoeffUnit);
    }

    public int updateHeatTransferCoeffUnit(HeatTransferCoeffUnit heatTransferCoeffUnit) {
        return heatTransferCoeffUnitMapper.updateByPrimaryKeySelective(heatTransferCoeffUnit);
    }

    public int deleteHeatTransferCoeffUnitById(Integer id) {
        return heatTransferCoeffUnitMapper.deleteByPrimaryKey(id);
    }
}
