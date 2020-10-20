package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.viscosity.ViscosityFitting;

import java.util.List;

public interface ViscosityFittingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ViscosityFitting record);

    int insertSelective(ViscosityFitting record);

    ViscosityFitting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ViscosityFitting record);

    int updateByPrimaryKey(ViscosityFitting record);

    List<ViscosityFitting> getAllViscosityFittings();

    List<ViscosityFitting> getViscosityFittingsByViscosityId(Integer did);

    int updateViscosityFittings(List<ViscosityFitting> viscosityFittings);

    int addViscosityFittingsByViscosityId(List<ViscosityFitting> viscosityFittings);
}