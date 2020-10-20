package com.cup.wang.airport.controller.management.liquid.heatCapacity;

import com.cup.wang.airport.model.heatCapacity.HeatCapacity;
import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.service.HeatCapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 21:40
 */
@RestController
@RequestMapping("/management/liquid/heatCapacity")
public class HeatCapacityController {

    @Autowired
    HeatCapacityService heatCapacityService;

    /*根据liquid_id查询*/
    @GetMapping("/{id}")
    public HeatCapacity getHeatCapacityByLiquidId(@PathVariable Integer id){
        return heatCapacityService.getHeatCapacityByLiquidId(id);
    }

    @PutMapping("/")
    public RespBean updateHeatCapacity(@RequestBody HeatCapacity heatCapacity){
        if (heatCapacityService.updateHeatCapacity(heatCapacity) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!!!");
    }

    //根据流体的id创建一行数据,liquidId=did,id自增并返回(此id即heat_capacity_id)
    @PostMapping("/{did}")
    public RespBean addHeatCapacityByLiquidId(@PathVariable Integer did){
        HeatCapacity heatCapacity = new HeatCapacity();
        heatCapacity.setLiquidId(did);
        if (heatCapacityService.addHeatCapacityByLiquidId(heatCapacity) == 1){
            Integer id = heatCapacity.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }
}
