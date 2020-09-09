package com.cup.wang.airport.controller.management;

import com.cup.wang.airport.model.ProjectUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.ProjectListService;
import com.cup.wang.airport.service.ProjectUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/7 9:10
 */
@RestController
@RequestMapping("/management/project/unit")
public class ProjectUnitController {

    @Autowired
    ProjectUnitService projectUnitService;

    @GetMapping("/{pid}")
    public ProjectUnit getById(@PathVariable Integer pid){
        return projectUnitService.getById(pid);
    }

    @PutMapping("/")
    public RespBean updateProjectUnit(@RequestBody ProjectUnit projectUnit){
        if (projectUnitService.updateProjectUnit(projectUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/")
    public RespBean addProjectUnit(@RequestBody ProjectUnit projectUnit){
        projectUnit.setDensityUnitId(1);
        projectUnit.setViscosityDynamicUnitId(1);
        projectUnit.setViscosityKinematicUnitId(1);
        if (projectUnitService.addProjectUnit(projectUnit) == 1) {
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }


}
