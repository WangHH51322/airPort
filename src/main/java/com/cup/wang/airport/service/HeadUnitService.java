package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.HeadUnitMapper;
import com.cup.wang.airport.model.HeadUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 19:58
 */
@Service
public class HeadUnitService {

    @Autowired
    HeadUnitMapper headUnitMapper;

    public List<HeadUnit> getAllHeadUnits() {
        return headUnitMapper.getAllHeadUnits();
    }

    public HeadUnit getHeadUnitById(Integer id) {
        return headUnitMapper.selectByPrimaryKey(id);
    }

    public int addHeadUnit(HeadUnit headUnit) {
        return headUnitMapper.insertSelective(headUnit);
    }

    public int updateHeadUnit(HeadUnit headUnit) {
        return headUnitMapper.updateByPrimaryKeySelective(headUnit);
    }

    public int deleteHeadUnitById(Integer id) {
        return headUnitMapper.deleteByPrimaryKey(id);
    }
}
