package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.DensityRelative;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.DensityRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:09
 */
@RestController
@RequestMapping("/management/liquid/density/relative")
public class DensityRelativeController {

    @Autowired
    DensityRelativeService densityRelativeService;

    @GetMapping("/")
    public List<DensityRelative> getAllDensityRelatives(){
        return densityRelativeService.getAllDensityRelatives();
    }

    @GetMapping("/{id}")
    public DensityRelative getDensityRelativeById(@PathVariable Integer id){
        return densityRelativeService.getDensityRelativeById(id);
    }

    @PutMapping("/")
    public RespBean updateDensityRelative(@RequestBody DensityRelative densityRelative){
        if (densityRelativeService.updateDensityRelative(densityRelative) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/")
    public RespBean addDensityRelative(@RequestBody DensityRelative densityRelative){
        if (densityRelativeService.addDensityRelative(densityRelative) == 1){
            Integer id = densityRelative.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    //根据密度的id创建一行数据,densityId=did,id自增并返回
    @PostMapping("/{did}")
    public RespBean addDensityRelativeByDensityId(@PathVariable Integer did){
        DensityRelative densityRelative = new DensityRelative();
        densityRelative.setDensityId(did);
        if (densityRelativeService.addDensityRelative(densityRelative) == 1){
            Integer id = densityRelative.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDensityRelativeById(@PathVariable Integer id){
        if (densityRelativeService.deleteDensityRelativeById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}

