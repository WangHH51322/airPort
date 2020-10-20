package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.density.DensityRelative;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:10
 */
public interface DensityRelativeMapper {
    List<DensityRelative> getAllDensityRelatives();

    DensityRelative getDensityRelativeById(Integer id);

    Integer updateDensityRelative(DensityRelative densityRelative);

    Integer addDensityRelative(DensityRelative densityRelative);

    Integer addDensityRelativeByDensityId(DensityRelative densityRelative);

    Integer deleteDensityRelativeById(Integer id);
}
