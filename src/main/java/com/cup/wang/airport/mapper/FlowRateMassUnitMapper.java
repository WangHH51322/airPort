package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.FlowRateMassUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 9:54
 */
public interface FlowRateMassUnitMapper extends JpaRepository<FlowRateMassUnit,Integer> {

    @Override
    List<FlowRateMassUnit> findAll();

    @Override
    Optional<FlowRateMassUnit> findById(Integer integer);

    @Override
    <S extends FlowRateMassUnit> S saveAndFlush(S s);

    @Override
    void deleteById(Integer integer);
}
