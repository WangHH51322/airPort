package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.FlowRateVolumetricUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 15:43
 */
public interface FlowRateVolumetricUnitMapper extends JpaRepository<FlowRateVolumetricUnit,Integer> {

    @Override
    List<FlowRateVolumetricUnit> findAll();

    @Override
    Optional<FlowRateVolumetricUnit> findById(Integer integer);

    @Override
    <S extends FlowRateVolumetricUnit> S saveAndFlush(S s);

    @Override
    void deleteById(Integer integer);
}
