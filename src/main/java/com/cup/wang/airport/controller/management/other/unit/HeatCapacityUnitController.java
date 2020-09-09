package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.HeatCapacityUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.HeatCapacityUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:04
 */
@RestController
@RequestMapping("/management/others/heartcapacity/unit")
public class HeatCapacityUnitController{

    @Autowired
    HeatCapacityUnitService heatCapacityUnitService;

    @GetMapping("/")
    public List<HeatCapacityUnit> getAllHeatCapacityUnits(){
        return heatCapacityUnitService.getAllHeatCapacityUnits();
    }

    @GetMapping("/{id}")
    public HeatCapacityUnit getHeatCapacityUnitById(@PathVariable Integer id){
        return heatCapacityUnitService.getHeatCapacityUnitById(id);
    }

    @PostMapping("/")
    public RespBean addHeatCapacityUnit(@RequestBody HeatCapacityUnit heatCapacityUnit){
        if (heatCapacityUnitService.addHeatCapacityUnit(heatCapacityUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateHeatCapacityUnit(@RequestBody HeatCapacityUnit heatCapacityUnit){
        if (heatCapacityUnitService.updateHeatCapacityUnit(heatCapacityUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHeatCapacityUnitById(@PathVariable Integer id){
        if (heatCapacityUnitService.deleteHeatCapacityUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
