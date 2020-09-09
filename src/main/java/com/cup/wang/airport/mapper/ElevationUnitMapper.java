package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.ElevationUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:27
 */
public interface ElevationUnitMapper extends JpaRepository<ElevationUnit,Integer> {
    List<ElevationUnit> findAll();

    @Override
    Optional<ElevationUnit> findById(Integer integer);
}
