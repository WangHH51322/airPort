package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.ViscosityDynamicUnit;

import java.util.List;

public interface ViscosityDynamicUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ViscosityDynamicUnit record);

    int insertSelective(ViscosityDynamicUnit record);

    ViscosityDynamicUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ViscosityDynamicUnit record);

    int updateByPrimaryKey(ViscosityDynamicUnit record);

    List<ViscosityDynamicUnit> getAllViscosityDynamicUnits();
}