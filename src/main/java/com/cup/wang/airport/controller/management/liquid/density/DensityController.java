package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.Density;
import com.cup.wang.airport.model.DensityUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.DensityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/3 17:45
 */
@RestController
@RequestMapping("/management/liquid/density/test")
public class DensityController {

    @Autowired
    DensityService densityTestService;

    @GetMapping("/{id}")
    public Density getDensityById(@PathVariable Integer id){
        return densityTestService.getDensityById(id);
    }

    @PutMapping("/")
    public RespBean updateDensity(@RequestBody Density density){
        if (densityTestService.updateDensity(density) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!!!");
    }

    @PostMapping("/")
    public RespBean addDensity(@RequestBody Density density){
        if (densityTestService.addDensity(density) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDensityById(@PathVariable Integer id){
        if (densityTestService.deleteDensityById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
