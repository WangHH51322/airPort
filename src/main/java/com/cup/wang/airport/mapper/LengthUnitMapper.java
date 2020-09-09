package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.LengthUnit;
import com.cup.wang.airport.model.TemperatureUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:48
 */
public interface LengthUnitMapper extends JpaRepository<LengthUnit,Integer> {
    List<LengthUnit> findAll();

    @Override
    Optional<LengthUnit> findById(Integer integer);

    @Override
    <S extends LengthUnit> S saveAndFlush(S s);

    @Override
    void deleteById(Integer integer);
}
