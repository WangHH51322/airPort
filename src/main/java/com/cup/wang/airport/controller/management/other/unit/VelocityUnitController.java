package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.VelocityUnit;
import com.cup.wang.airport.service.VelocityUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:17
 */
@RestController
@RequestMapping("/management/others/velocity/unit")
public class VelocityUnitController {

    @Autowired
    VelocityUnitService velocityUnitService;

    @GetMapping("/")
    public List<VelocityUnit> getAllVelocityUnits(){
        return velocityUnitService.getAllVelocityUnits();
    }

    @GetMapping("/{id}")
    public VelocityUnit getVelocityUnitById(@PathVariable Integer id){
        return velocityUnitService.getVelocityUnitById(id);
    }

    @PostMapping("/")
    public RespBean addVelocityUnit(@RequestBody VelocityUnit velocityUnit){
        if (velocityUnitService.addVelocityUnit(velocityUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateVelocityUnit(@RequestBody VelocityUnit velocityUnit){
        if (velocityUnitService.updateVelocityUnit(velocityUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteVelocityUnitById(@PathVariable Integer id){
        if (velocityUnitService.deleteVelocityUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
