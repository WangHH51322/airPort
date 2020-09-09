package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.HeadUnit;

import java.util.List;

public interface HeadUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeadUnit record);

    int insertSelective(HeadUnit record);

    HeadUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeadUnit record);

    int updateByPrimaryKey(HeadUnit record);

    List<HeadUnit> getAllHeadUnits();
}