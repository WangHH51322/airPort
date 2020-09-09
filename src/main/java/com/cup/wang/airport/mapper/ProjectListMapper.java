package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.ProjectList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectList record);

    int insertSelective(ProjectList record);

    ProjectList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectList record);

    int updateByPrimaryKey(ProjectList record);

    List<ProjectList> getAll();

    List<ProjectList> getAllByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keyword")String keyword);

    Long getTotal(String keyword);
}