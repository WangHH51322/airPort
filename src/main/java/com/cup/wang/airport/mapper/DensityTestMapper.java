package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.DensityTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DensityTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DensityTest record);

    int insertSelective(DensityTest record);

    DensityTest selectByPrimaryKey(Integer integer);

    int updateByPrimaryKeySelective(DensityTest record);

    int updateByPrimaryKey(DensityTest record);

    List<DensityTest> selectByKey(@Param("id") Integer id, @Param("densityValueId")Integer densityValueId);
    List<DensityTest> selectByKey2(@Param("id") Integer id, @Param("densityValueId")Integer densityValueId);

    DensityTest selectConstantByKey(Integer id);
}