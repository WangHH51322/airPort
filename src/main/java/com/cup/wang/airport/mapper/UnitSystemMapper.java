package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.UnitSystem;

import java.util.List;

public interface UnitSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UnitSystem record);

    int insertSelective(UnitSystem record);

    UnitSystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UnitSystem record);

    int updateByPrimaryKey(UnitSystem record);

    List<UnitSystem> getAll();
}