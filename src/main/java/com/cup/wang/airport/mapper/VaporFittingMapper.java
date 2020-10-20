package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.vapor.VaporFitting;

import java.util.List;

public interface VaporFittingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VaporFitting record);

    int insertSelective(VaporFitting record);

    VaporFitting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VaporFitting record);

    int updateByPrimaryKey(VaporFitting record);

    List<VaporFitting> getAllVaporFittings();

    List<VaporFitting> getVaporFittingsByVaporId(Integer did);

    int updateVaporFittings(List<VaporFitting> vaporFittings);

    int addVaporFittingsByVaporId(List<VaporFitting> vaporFittings);
}