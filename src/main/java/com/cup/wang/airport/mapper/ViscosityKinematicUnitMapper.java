package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.ViscosityKinematicUnit;

import java.util.List;

public interface ViscosityKinematicUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ViscosityKinematicUnit record);

    int insertSelective(ViscosityKinematicUnit record);

    ViscosityKinematicUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ViscosityKinematicUnit record);

    int updateByPrimaryKey(ViscosityKinematicUnit record);

    List<ViscosityKinematicUnit> getAllHeadUnitViscosityKinematicUnits();
}