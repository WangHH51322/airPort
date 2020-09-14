package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ProjectUnitMapper;
import com.cup.wang.airport.model.ProjectUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/13 21:36
 */
@Service
public class ProjectUnitService {

    @Autowired
    ProjectUnitMapper projectUnitMapper;

    public List<ProjectUnit> getProjectUnitByProjectId(Integer id) {
        return projectUnitMapper.getProjectUnitByProjectId(id);
    }

    public int updateProjectUnit(ProjectUnit projectUnit) {
        return projectUnitMapper.updateByPrimaryKeySelective(projectUnit);
    }
}
