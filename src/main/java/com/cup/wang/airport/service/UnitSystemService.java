package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.UnitSystemMapper;
import com.cup.wang.airport.model.unit.UnitSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/9 15:20
 */
@Service
public class UnitSystemService {

    @Autowired
    UnitSystemMapper unitSystemMapper;

    public List<UnitSystem> getAll() {
        return unitSystemMapper.getAll();
    }

    public UnitSystem getById(Integer id) {
        return unitSystemMapper.selectByPrimaryKey(id);
    }

    public int addUnitSystem(UnitSystem unitSystem) {
        return unitSystemMapper.insertSelective(unitSystem);
    }

    public int updateUnitSystem(UnitSystem unitSystem) {
        return unitSystemMapper.updateByPrimaryKey(unitSystem);
    }

    public int deleteUnitSystem(Integer id) {
        return unitSystemMapper.deleteByPrimaryKey(id);
    }
}
