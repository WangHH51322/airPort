package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.TimeUnit;
import com.cup.wang.airport.service.TimeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:43
 */
@RestController
@RequestMapping("/management/others/time/unit")
public class TimeUnitController {

    @Autowired
    TimeUnitService timeUnitService;

    @GetMapping("/")
    public List<TimeUnit> getAllTimeUnits(){
        return timeUnitService.getAllTimeUnits();
    }

    @GetMapping("/{id}")
    public TimeUnit getTimeUnitById(@PathVariable Integer id){
        return timeUnitService.getTimeUnitById(id);
    }

    @PostMapping("/")
    public RespBean addTimeUnit(@RequestBody TimeUnit timeUnit){
        if (timeUnitService.addTimeUnit(timeUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateTimeUnit(@RequestBody TimeUnit timeUnit){
        if (timeUnitService.updateTimeUnit(timeUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteTimeUnitById(@PathVariable Integer id){
        if (timeUnitService.deleteTimeUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
