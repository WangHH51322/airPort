package com.cup.wang.airport.controller.management;

import com.cup.wang.airport.model.DensityUnit;
import com.cup.wang.airport.model.ProjectList;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.RespPageBean;
import com.cup.wang.airport.service.ProjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/6 10:44
 */
@RestController
@RequestMapping("/management/project")
public class ProjectListController {

    @Autowired
    ProjectListService projectListService;

    @GetMapping("/page/")
    public RespPageBean getAllByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10")Integer size,
                                     String keyword){
        return projectListService.getAllByPage(page,size,keyword);
    }

    @GetMapping("/")
    public List<ProjectList> getAll(){
        return projectListService.getAll();
    }

    @GetMapping("/{id}")
    public ProjectList getById(@PathVariable Integer id){
        return projectListService.getById(id);
    }

    @PutMapping("/")
    public RespBean updateProject(@RequestBody ProjectList projectList){
        if (projectListService.updateProject(projectList) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/returnId/")
    public RespBean addProjectAndReturnId(@RequestBody ProjectList projectList){
        Integer integer = projectListService.addProjectAndReturnId(projectList);
        Integer id = projectList.getId();
        if ( integer == 1){
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteProjectById(@PathVariable Integer id){
        if (projectListService.deleteProjectById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
