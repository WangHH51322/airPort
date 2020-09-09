package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.ProjectUnit;

public interface ProjectUnitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectUnit record);

    int insertSelective(ProjectUnit record);

    ProjectUnit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectUnit record);

    int updateByPrimaryKey(ProjectUnit record);
}