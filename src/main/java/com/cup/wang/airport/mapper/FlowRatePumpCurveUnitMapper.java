package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.FlowRatePumpCurveUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 15:20
 */
public interface FlowRatePumpCurveUnitMapper extends JpaRepository<FlowRatePumpCurveUnit,Integer> {

    @Override
    List<FlowRatePumpCurveUnit> findAll();

    @Override
    Optional<FlowRatePumpCurveUnit> findById(Integer integer);

    @Override
    <S extends FlowRatePumpCurveUnit> S saveAndFlush(S s);

    @Override
    void deleteById(Integer integer);
}
