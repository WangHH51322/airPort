package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.Density;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:59
 */
public interface DensityMapper {
    List<Density> getAllDensities();

    Density getDensityById(Integer id);
}
