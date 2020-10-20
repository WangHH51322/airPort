package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.vapor.Vapor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VaporMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vapor record);

    int insertSelective(Vapor record);

    Vapor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vapor record);

    int updateByPrimaryKey(Vapor record);

    List<Vapor> getVaporByLiquidId(@Param("id") Integer id, @Param("vaporValueId") Integer vaporValueId);
}