package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.Valve;

import java.util.List;

public interface ValveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Valve record);

    int insertSelective(Valve record);

    Valve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Valve record);

    int updateByPrimaryKey(Valve record);

    List<Valve> getValvesByProjectId(Integer id);
}