package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.Density;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DensityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Density record);

    int insertSelective(Density record);

    Density selectByPrimaryKey(Integer integer);

    int updateByPrimaryKeySelective(Density record);

    int updateByPrimaryKey(Density record);

    List<Density> selectByKey(@Param("id") Integer id, @Param("densityValueId")Integer densityValueId);
    List<Density> selectByKey2(@Param("id") Integer id, @Param("densityValueId")Integer densityValueId);

    Density selectConstantByKey(Integer id);
}