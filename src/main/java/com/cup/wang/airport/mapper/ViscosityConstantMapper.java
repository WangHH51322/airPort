package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.viscosity.ViscosityConstant;

import java.util.List;

public interface ViscosityConstantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ViscosityConstant record);

    int insertSelective(ViscosityConstant record);

    ViscosityConstant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ViscosityConstant record);

    int updateByPrimaryKey(ViscosityConstant record);

    List<ViscosityConstant> getAllViscosityConstants();

    ViscosityConstant getViscosityConstantByDensityId(Integer did);
}