package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.density.DensityFitting;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:02
 */
public interface DensityFittingMapper {
    List<DensityFitting> getAllDensityFittings();

    List<DensityFitting> getDensityFittingsByDensityId(Integer did);

    Integer updateDensityFitting(DensityFitting densityFitting);

    Integer addDensityFitting(DensityFitting densityFitting);

    Integer deleteDensityFittingById(Integer id);

    int updateDensityFittings(List<DensityFitting> densityFittings);

    int addDensityFittingsByDensityId(List<DensityFitting> densityFittings);
}
