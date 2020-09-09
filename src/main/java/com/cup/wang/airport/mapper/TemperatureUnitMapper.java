package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.TemperatureUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/22 18:16
 */
public interface TemperatureUnitMapper extends JpaRepository<TemperatureUnit,Integer>{
    //JpaRepository<x1,x2>  JpaRepository后边的参数x1指要创建表格的实体类,x2表示实体类中Id属性的类型

    List<TemperatureUnit> findAll();

    Optional<TemperatureUnit> findById(Integer id);

}
