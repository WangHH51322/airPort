package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.mapper.TemperatureUnitMapper;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.TemperatureUnit;
import com.cup.wang.airport.service.TemperatureUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/22 18:33
 */
@RestController
@RequestMapping("/management/others/temperature/unit")
public class TemperatureUnitController {

    @Autowired
    TemperatureUnitService temperatureUnitService;


    @GetMapping("/")
    public List<TemperatureUnit> getAllTemperatureUnit(){
        return temperatureUnitService.getAllTemperatureUnit();
    }

    @GetMapping("/{id}")
    public Optional<TemperatureUnit> getTemperatureUnitById(@PathVariable Integer id){
        return temperatureUnitService.getTemperatureUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateTemperatureUnit(@RequestBody TemperatureUnit temperatureUnit){
        if (temperatureUnitService.updateTemperatureUnit(temperatureUnit) != null){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/")
    public RespBean addTemperatureUnit(@RequestBody TemperatureUnit temperatureUnit){
        if (temperatureUnitService.addTemperatureUnit(temperatureUnit) != null){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteTemperatureUnitById(@PathVariable Integer id){

        try {
            temperatureUnitService.deleteTemperatureUnitById(id);
            return RespBean.ok("删除成功!");
        } catch (Exception e) {
            return RespBean.error("删除失败!");
        }
    }

}
