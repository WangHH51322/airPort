package com.cup.wang.airport.controller.unit;

import com.cup.wang.airport.model.project.ProjectUnit;
import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.service.ProjectUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/13 21:36
 */
@RestController
@RequestMapping("/unit/projectUnit")
public class ProjectUnitController {

    @Autowired
    ProjectUnitService projectUnitService;

    @GetMapping("/projectId/{id}")
    public List<ProjectUnit> getProjectUnitByProjectId(@PathVariable Integer id){
        return projectUnitService.getProjectUnitByProjectId(id);
    }

    @PutMapping("/")
    public RespBean updateProjectUnit(@RequestBody ProjectUnit projectUnit){
        if (projectUnitService.updateProjectUnit(projectUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
