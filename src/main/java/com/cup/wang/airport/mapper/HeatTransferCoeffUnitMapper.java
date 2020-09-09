package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.HeatTransferCoeffUnit;

import java.util.List;

public interface HeatTransferCoeffUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HeatTransferCoeffUnit record);

    int insertSelective(HeatTransferCoeffUnit record);

    HeatTransferCoeffUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HeatTransferCoeffUnit record);

    int updateByPrimaryKey(HeatTransferCoeffUnit record);

    List<HeatTransferCoeffUnit> getAllHeatTransferCoeffUnits();

}