package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.DensityFitting;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.DensityFittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:01
 */
@RestController
@RequestMapping("/management/liquid/density/fitting")
public class DensityFittingController {

    @Autowired
    DensityFittingService densityFittingService;

    @GetMapping("/")
    public List<DensityFitting> getAllDensityFittings(){
        return densityFittingService.getAllDensityFittings();
    }

    //根据density_id获取一整组的数据
    @GetMapping("/{did}")
    public List<DensityFitting> getDensityFittingsByDensityId(@PathVariable Integer did){
        return densityFittingService.getDensityFittingsByDensityId(did);
    }

    @PutMapping("/")
    public RespBean updateDensityFitting(@RequestBody DensityFitting densityFitting){
        if (densityFittingService.updateDensityFitting(densityFitting) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/")
    public RespBean addDensityFitting(@RequestBody DensityFitting densityFitting){
        if (densityFittingService.addDensityFitting(densityFitting) == 1){
            Integer id = densityFitting.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    //根据密度的id创建一行数据,densityId=did,id自增并返回
    @PostMapping("/{did}")
    public RespBean addDensityFittingByDensityId(@PathVariable Integer did){
        DensityFitting densityFitting = new DensityFitting();
        densityFitting.setDensityId(did);
        if (densityFittingService.addDensityFitting(densityFitting) == 1){
            Integer id = densityFitting.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDensityFittingById(@PathVariable Integer id){
        if (densityFittingService.deleteDensityFittingById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}

