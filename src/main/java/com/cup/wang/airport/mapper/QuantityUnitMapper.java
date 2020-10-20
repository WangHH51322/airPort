package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.unit.QuantityUnit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuantityUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuantityUnit record);

    int insertSelective(QuantityUnit record);

    QuantityUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuantityUnit record);

    int updateByPrimaryKey(QuantityUnit record);

    List<QuantityUnit> getAllQuantityUnit();

    List<QuantityUnit> getQuantityUnitByPhysicalQuantityId(Integer id);

    List<QuantityUnit> getQuantityUnitByPhysicalQuantityIdAndUnitSystemId(@Param("id1") Integer id1,@Param("id2") Integer id2);

    List<QuantityUnit> getQuantityUnitByUnitSystemId(Integer id);
}