package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.WallThicknessUnitMapper;
import com.cup.wang.airport.model.WallThicknessUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:05
 */
@Service
public class WallThicknessUnitService {

    @Autowired
    WallThicknessUnitMapper wallThicknessUnitMapper;

    public List<WallThicknessUnit> getAllWallThicknessUnits() {
        return wallThicknessUnitMapper.getAllWallThicknessUnits();
    }

    public WallThicknessUnit getWallThicknessUnitById(Integer id) {
        return wallThicknessUnitMapper.selectByPrimaryKey(id);
    }

    public int addWallThicknessUnit(WallThicknessUnit wallThicknessUnit) {
        return wallThicknessUnitMapper.insertSelective(wallThicknessUnit);
    }

    public int updateWallThicknessUnit(WallThicknessUnit wallThicknessUnit) {
        return wallThicknessUnitMapper.updateByPrimaryKeySelective(wallThicknessUnit);
    }

    public int deleteWallThicknessUnitById(Integer id) {
        return wallThicknessUnitMapper.deleteByPrimaryKey(id);
    }
}
