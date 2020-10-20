package com.cup.wang.airport.controller.management.liquid.heatCapacity;

import com.cup.wang.airport.model.heatCapacity.HeatCapacityConstant;
import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.service.HeatCapacityConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 21:02
 */
@RestController
@RequestMapping("/management/liquid/heatCapacity/constant")
public class HeatCapacityConstantController {

    @Autowired
    HeatCapacityConstantService heatCapacityConstantService;

    @GetMapping("/")
    public List<HeatCapacityConstant> getAllHeatCapacityConstants(){
        return heatCapacityConstantService.getAllHeatCapacityConstants();
    }

    //根据heatCapacity_id获取一整组的数据
    @GetMapping("/{did}")
    public HeatCapacityConstant getHeatCapacityConstantByHCId(@PathVariable Integer did){
        return heatCapacityConstantService.getHeatCapacityConstantByHCId(did);
    }

    @PutMapping("/")
    public RespBean updateHeatCapacityConstant(@RequestBody HeatCapacityConstant heatCapacityConstant){
        if (heatCapacityConstantService.updateHeatCapacityConstant(heatCapacityConstant) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    //根据热容的id创建一行数据,heatCapacityId=did,id自增并返回
    @PostMapping("/{did}")
    public RespBean addHeatCapacityConstantByHCId(@PathVariable Integer did){
        HeatCapacityConstant heatCapacityConstant = new HeatCapacityConstant();
        heatCapacityConstant.setHeatCapacityId(did);
        if (heatCapacityConstantService.addHeatCapacityConstantByHCId(heatCapacityConstant) == 1){
            Integer id = heatCapacityConstant.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHeatCapacityConstantById(@PathVariable Integer id){
        if (heatCapacityConstantService.deleteHeatCapacityConstantById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return  RespBean.error("删除失败!");
    }
}
