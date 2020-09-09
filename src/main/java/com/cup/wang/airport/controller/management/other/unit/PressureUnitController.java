package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.PowerUnit;
import com.cup.wang.airport.model.PressureUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.PowerUnitService;
import com.cup.wang.airport.service.PressureUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:31
 */
@RestController
@RequestMapping("/management/others/pressure/unit")
public class PressureUnitController {

    @Autowired
    PressureUnitService pressureUnitService;

    @GetMapping("/")
    public List<PressureUnit> getAllPressureUnits(){
        return pressureUnitService.getAllPressureUnits();
    }

    @GetMapping("/{id}")
    public PressureUnit getPressureUnitById(@PathVariable Integer id){
        return pressureUnitService.getPressureUnitById(id);
    }

    @PostMapping("/")
    public RespBean addPressureUnit(@RequestBody PressureUnit pressureUnit){
        if (pressureUnitService.addPressureUnit(pressureUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updatePressureUnit(@RequestBody PressureUnit pressureUnit){
        if (pressureUnitService.updatePressureUnit(pressureUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePressureUnitById(@PathVariable Integer id){
        if (pressureUnitService.deletePressureUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
