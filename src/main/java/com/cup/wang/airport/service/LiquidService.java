package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityMapper;
import com.cup.wang.airport.mapper.LiquidMapper;
import com.cup.wang.airport.model.Liquid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 22:52
 */
@Service
public class LiquidService {

    @Autowired
    LiquidMapper liquidMapper;
    @Autowired
    DensityMapper densityMapper;

    public Integer updateLiquidById(Liquid liquid) {
        return liquidMapper.updateLiquidById(liquid);
    }

    public List<Liquid> getAllLiquids() {
        return liquidMapper.getAllLiquids();
    }

    public Integer addLiquid(Liquid liquid) {
        return liquidMapper.addLiquid(liquid);
    }
}
