package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.HeatValueVolUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.HeatValueVolUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:55
 */
@RestController
@RequestMapping("/management/others/heatvaluevol/unit")
public class HeatValueVolUnitController {

    @Autowired
    HeatValueVolUnitService heatValueVolUnitService;

    @GetMapping("/")
    public List<HeatValueVolUnit> getAllHeatValueVolUnits(){
        return heatValueVolUnitService.getAllHeatValueVolUnits();
    }

    @GetMapping("/{id}")
    public HeatValueVolUnit getHeatValueVolUnitById(@PathVariable Integer id){
        return heatValueVolUnitService.getHeatValueVolUnitById(id);
    }

    @PostMapping("/")
    public RespBean addHeatValueVolUnit(@RequestBody HeatValueVolUnit heatValueVolUnit){
        if (heatValueVolUnitService.addHeatValueVolUnit(heatValueVolUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateHeatValueVolUnit(@RequestBody HeatValueVolUnit heatValueVolUnit){
        if (heatValueVolUnitService.updateHeatValueVolUnit(heatValueVolUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHeatValueVolUnitById(@PathVariable Integer id){
        if (heatValueVolUnitService.deleteHeatValueVolUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
