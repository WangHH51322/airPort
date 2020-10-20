package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.Node;

import java.util.List;

public interface NodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Node record);

    int insertSelective(Node record);

    Node selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Node record);

    int updateByPrimaryKey(Node record);

    List<Node> getNodesByProjectId(Integer id);
}