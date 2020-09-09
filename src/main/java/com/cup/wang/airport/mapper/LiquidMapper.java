package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.Liquid;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 22:52
 */
public interface LiquidMapper {
    Liquid getLiquidById(Integer id);

    Integer updateLiquidById(Liquid liquid);

    List<Liquid> getAllLiquids();

    Integer addLiquid(Liquid liquid);
}
