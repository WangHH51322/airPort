package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.TimeUnitMapper;
import com.cup.wang.airport.model.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:43
 */
@Service
public class TimeUnitService {

    @Autowired
    TimeUnitMapper timeUnitMapper;


    public List<TimeUnit> getAllTimeUnits() {
        return timeUnitMapper.getAllTimeUnits();
    }

    public TimeUnit getTimeUnitById(Integer id) {
        return timeUnitMapper.selectByPrimaryKey(id);
    }

    public int addTimeUnit(TimeUnit timeUnit) {
        return timeUnitMapper.insertSelective(timeUnit);
    }

    public int updateTimeUnit(TimeUnit timeUnit) {
        return timeUnitMapper.updateByPrimaryKeySelective(timeUnit);
    }

    public int deleteTimeUnitById(Integer id) {
        return timeUnitMapper.deleteByPrimaryKey(id);
    }
}
