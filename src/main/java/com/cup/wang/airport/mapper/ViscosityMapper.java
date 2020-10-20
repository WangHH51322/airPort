package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.viscosity.Viscosity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ViscosityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Viscosity record);

    int insertSelective(Viscosity record);

    Viscosity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Viscosity record);

    int updateByPrimaryKey(Viscosity record);

    List<Viscosity> getViscosityByLiquidId(@Param("id") Integer id, @Param("viscosityValueId") Integer viscosityValueId);
}