package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.RoughnessUnit;

import java.util.List;

public interface RoughnessUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoughnessUnit record);

    int insertSelective(RoughnessUnit record);

    RoughnessUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoughnessUnit record);

    int updateByPrimaryKey(RoughnessUnit record);

    List<RoughnessUnit> getAllRoughnessUnits();
}