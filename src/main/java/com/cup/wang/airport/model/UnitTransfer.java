package com.cup.wang.airport.model;

import com.cup.wang.airport.mapper.QuantityUnitMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/12 14:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class UnitTransfer implements Serializable {

    @Autowired
    QuantityUnitMapper quantityUnitMapper;

    private Integer quantityUnitIdA;
    private Integer quantityUnitIdB;
    private Double quantityUnitValueA;
    private Double quantityUnitValueB;

    public Double transfer(Integer quantityUnitIdA,Integer quantityUnitIdB,Double quantityUnitValueB){

        this.quantityUnitIdA = quantityUnitIdA;
        this.quantityUnitIdB = quantityUnitIdB;
        this.quantityUnitValueB = quantityUnitValueB;
        //转换前的物理量的转换系数
        double factorA2 = quantityUnitMapper.selectByPrimaryKey(quantityUnitIdB).getFactorA();
        double factorB2 = quantityUnitMapper.selectByPrimaryKey(quantityUnitIdB).getFactorB();
        //中间值(一般是物理量的标准单位)
        double middleValue = factorA2 * quantityUnitValueB + factorB2;
        //转换后的物理量的转换系数
        double factorA1 = quantityUnitMapper.selectByPrimaryKey(quantityUnitIdA).getFactorA();
        double factorB1 = quantityUnitMapper.selectByPrimaryKey(quantityUnitIdA).getFactorB();
        //转换后的物理量的值
        double finalValue = (middleValue - factorB1) / factorA1;

        return finalValue;
    }

}
