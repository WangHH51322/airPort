package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.DiameterUnit;
import com.cup.wang.airport.model.TemperatureUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:08
 */
public interface DiameterUnitMapper extends JpaRepository<DiameterUnit,Integer> {
    List<DiameterUnit> findAll();

    Optional<DiameterUnit> findById(Integer id);
}
