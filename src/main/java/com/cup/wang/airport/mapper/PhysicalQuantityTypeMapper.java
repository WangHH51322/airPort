package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.PhysicalQuantityType;

import java.util.List;

public interface PhysicalQuantityTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhysicalQuantityType record);

    int insertSelective(PhysicalQuantityType record);

    PhysicalQuantityType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhysicalQuantityType record);

    int updateByPrimaryKey(PhysicalQuantityType record);

    List<PhysicalQuantityType> getAllPhysicalQuantityTypes();
}