package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.ViscosityDynamicUnit;
import com.cup.wang.airport.service.ViscosityDynamicUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:27
 */
@RestController
@RequestMapping("/management/viscositydynamic/head/unit")
public class ViscosityDynamicUnitController {

    @Autowired
    ViscosityDynamicUnitService viscosityDynamicUnitService;

    @GetMapping("/")
    public List<ViscosityDynamicUnit> getAllViscosityDynamicUnits(){
        return viscosityDynamicUnitService.getAllViscosityDynamicUnits();
    }

    @GetMapping("/{id}")
    public ViscosityDynamicUnit getViscosityDynamicUnitById(@PathVariable Integer id){
        return viscosityDynamicUnitService.getViscosityDynamicUnitById(id);
    }

    @PostMapping("/")
    public RespBean addViscosityDynamicUnit(@RequestBody ViscosityDynamicUnit viscosityDynamicUnit){
        if (viscosityDynamicUnitService.addViscosityDynamicUnit(viscosityDynamicUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateViscosityDynamicUnit(@RequestBody ViscosityDynamicUnit viscosityDynamicUnit){
        if (viscosityDynamicUnitService.updateViscosityDynamicUnit(viscosityDynamicUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteViscosityDynamicUnitById(@PathVariable Integer id){
        if (viscosityDynamicUnitService.deleteViscosityDynamicUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
