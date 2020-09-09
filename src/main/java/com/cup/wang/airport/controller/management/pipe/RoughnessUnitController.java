package com.cup.wang.airport.controller.management.pipe;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.RoughnessUnit;
import com.cup.wang.airport.service.RoughnessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 16:54
 */
@RestController
@RequestMapping("/management/pipe/roughness/unit")
public class RoughnessUnitController {

    @Autowired
    RoughnessUnitService roughnessUnitService;

    @GetMapping("/")
    public List<RoughnessUnit> getAllRoughnessUnits(){
        return roughnessUnitService.getAllRoughnessUnits();
    }

    @GetMapping("/{id}")
    public RoughnessUnit getRoughnessUnitById(@PathVariable Integer id){
        return roughnessUnitService.getRoughnessUnitById(id);
    }

    @PostMapping("/")
    public RespBean addRoughnessUnit(@RequestBody RoughnessUnit roughnessUnit){
        if (roughnessUnitService.addRoughnessUnit(roughnessUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateRoughnessUnit(@RequestBody RoughnessUnit roughnessUnit){
        if (roughnessUnitService.updateRoughnessUnit(roughnessUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteRoughnessUnitById(@PathVariable Integer id){
        if (roughnessUnitService.deleteRoughnessUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
