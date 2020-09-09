package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ProjectListMapper;
import com.cup.wang.airport.model.ProjectList;
import com.cup.wang.airport.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/6 10:43
 */
@Service
public class ProjectListService {
    @Autowired
    ProjectListMapper  projectListMapper;

    public List<ProjectList> getAll() {
        return projectListMapper.getAll();
    }

    public ProjectList getById(Integer id) {
        return projectListMapper.selectByPrimaryKey(id);
    }

    public int updateProject(ProjectList projectList) {
        projectList.setChangeDate(new Date());
        return projectListMapper.updateByPrimaryKeySelective(projectList);
    }

    public Integer addProjectAndReturnId(ProjectList projectList) {
        projectList.setCreateDate(new Date());
        projectList.setChangeDate(new Date());
        return projectListMapper.insertSelective(projectList);
    }

    public int deleteProjectById(Integer id) {
        return projectListMapper.deleteByPrimaryKey(id);
    }

    public RespPageBean getAllByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null){
            page = (page - 1) * size;
        }
        List<ProjectList> data = projectListMapper.getAllByPage(page,size,keyword);
        Long total = projectListMapper.getTotal(keyword);
        RespPageBean respPageBean = new RespPageBean();
        respPageBean.setData(data);
        respPageBean.setTotal(total);
        return respPageBean;
    }
}
