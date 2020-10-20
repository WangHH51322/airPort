package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.Pipe;

import java.util.List;

public interface PipeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pipe record);

    int insertSelective(Pipe record);

    Pipe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pipe record);

    int updateByPrimaryKey(Pipe record);

    List<Pipe> getPipesByProjectId(Integer id);
}