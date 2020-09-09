package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.TimeUnit;

import java.util.List;

public interface TimeUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeUnit record);

    int insertSelective(TimeUnit record);

    TimeUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeUnit record);

    int updateByPrimaryKey(TimeUnit record);

    List<TimeUnit> getAllTimeUnits();
}