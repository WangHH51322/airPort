package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.vapor.VaporConstant;

import java.util.List;

public interface VaporConstantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VaporConstant record);

    int insertSelective(VaporConstant record);

    VaporConstant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VaporConstant record);

    int updateByPrimaryKey(VaporConstant record);

    List<VaporConstant> getAllVaporConstants();

    VaporConstant getVaporConstantByVaporId(Integer did);
}