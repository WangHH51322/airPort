package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.unit.PhysicalQuantity;

import java.util.List;

public interface PhysicalQuantityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhysicalQuantity record);

    int insertSelective(PhysicalQuantity record);

    PhysicalQuantity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhysicalQuantity record);

    int updateByPrimaryKey(PhysicalQuantity record);

    List<PhysicalQuantity> getAllPhysicalQuantity();

    List<PhysicalQuantity> getAllPhysicalQuantitySimple();

    PhysicalQuantity getSimplePhysicalQuantityById(Integer id);

}