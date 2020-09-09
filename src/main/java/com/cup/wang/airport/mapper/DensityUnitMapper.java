package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.DensityUnit;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:13
 */
public interface DensityUnitMapper {
    List<DensityUnit> getAllDensityUnit();

    Integer updateByPrimaryKeySelective(DensityUnit densityUnit);

    Integer insertSelective(DensityUnit densityUnit);

    Integer deleteByPrimaryKey(Integer id);

    Integer addDensityUnitAndReturnId(DensityUnit densityUnit);

    DensityUnit getDensityUnitById(Integer id);
}
