package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.WallThicknessUnit;

import java.util.List;

public interface WallThicknessUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WallThicknessUnit record);

    int insertSelective(WallThicknessUnit record);

    WallThicknessUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WallThicknessUnit record);

    int updateByPrimaryKey(WallThicknessUnit record);

    List<WallThicknessUnit> getAllWallThicknessUnits();
}