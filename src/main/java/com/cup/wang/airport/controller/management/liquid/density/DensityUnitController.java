package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.density.DensityUnit;
import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.service.DensityUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:12
 */
@RestController
@RequestMapping("/management/liquid/density/unit")
public class DensityUnitController {

    @Autowired
    DensityUnitService densityUnitService;

    @GetMapping("/")
    public List<DensityUnit> getAllDensityUnit(){
        return densityUnitService.getAllDensityUnit();
    }

    @GetMapping("/{id}")
    public DensityUnit getDensityUnitById(@PathVariable Integer id){
        return densityUnitService.getDensityUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateDensityUnit(@RequestBody DensityUnit densityUnit){
        if (densityUnitService.updateDensityUnit(densityUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!!!");
    }

    @PostMapping("/")
    public RespBean addDensityUnit(@RequestBody DensityUnit densityUnit){
        if (densityUnitService.addDensityUnit(densityUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PostMapping("/returnId/")
    public RespBean addDensityUnitAndReturnId(@RequestBody DensityUnit densityUnit){
        Integer integer = densityUnitService.addDensityUnitAndReturnId(densityUnit);
        Integer id = densityUnit.getId();
        if ( integer == 1){
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDensityUnitById(@PathVariable Integer id){
        if (densityUnitService.deleteDensityUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}

