package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ProjectListMapper;
import com.cup.wang.airport.mapper.ProjectUnitMapper;
import com.cup.wang.airport.model.ProjectUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/7 9:10
 */
@Service
public class ProjectUnitService {

    @Autowired
    ProjectUnitMapper projectUnitMapper;

    public ProjectUnit getById(Integer id) {
        return projectUnitMapper.selectByPrimaryKey(id);
    }

    public int updateProjectUnit(ProjectUnit projectUnit) {
        return projectUnitMapper.updateByPrimaryKeySelective(projectUnit);
    }

    public int addProjectUnit(ProjectUnit projectUnit) {
        return projectUnitMapper.insertSelective(projectUnit);
    }
}
