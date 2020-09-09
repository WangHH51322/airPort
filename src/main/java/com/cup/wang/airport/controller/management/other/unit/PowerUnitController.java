package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.PowerUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.PowerUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:07
 */
@RestController
@RequestMapping("/management/others/power/unit")
public class PowerUnitController {

    @Autowired
    PowerUnitService powerUnitService;

    @GetMapping("/")
    public List<PowerUnit> getAllPowerUnits(){
        return powerUnitService.getAllPowerUnits();
    }

    @GetMapping("/{id}")
    public PowerUnit getPowerUnitById(@PathVariable Integer id){
        return powerUnitService.getPowerUnitById(id);
    }

    @PostMapping("/")
    public RespBean addPowerUnit(@RequestBody PowerUnit powerUnit){
        if (powerUnitService.addPowerUnit(powerUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updatePowerUnit(@RequestBody PowerUnit powerUnit){
        if (powerUnitService.updatePowerUnit(powerUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePowerUnitById(@PathVariable Integer id){
        if (powerUnitService.deletePowerUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
