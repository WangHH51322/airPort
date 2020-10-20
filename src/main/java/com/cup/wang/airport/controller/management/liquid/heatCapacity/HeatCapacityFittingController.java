package com.cup.wang.airport.controller.management.liquid.heatCapacity;

import com.cup.wang.airport.model.heatCapacity.HeatCapacityFitting;
import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.service.HeatCapacityFittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 21:18
 */
@RestController
@RequestMapping("/management/liquid/heatCapacity/fitting")
public class HeatCapacityFittingController {

    @Autowired
    HeatCapacityFittingService heatCapacityFittingService;

    @GetMapping("/")
    public List<HeatCapacityFitting> getAllHeatCapacityFittings(){
        return heatCapacityFittingService.getAllHeatCapacityFittings();
    }

    //根据heatCapacity_id获取一整组的数据
    @GetMapping("/{did}")
    public List<HeatCapacityFitting> getHeatCapacityFittingsByHCId(@PathVariable Integer did){
        return heatCapacityFittingService.getHeatCapacityFittingsByHCId(did);
    }

    @PutMapping("/")
    public RespBean updateHeatCapacityFittings(@RequestBody List<HeatCapacityFitting> heatCapacityFittings){
        if (heatCapacityFittingService.updateHeatCapacityFittings(heatCapacityFittings) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /*HeatCapacityFitting,需要提前将heatCapacity_id给赋值*/
    @PostMapping("/")
    public RespBean addHeatCapacityFittingsByHCId(@RequestBody List<HeatCapacityFitting> heatCapacityFittings){
        if(heatCapacityFittingService.addHeatCapacityFittingsByHCId(heatCapacityFittings) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
}
