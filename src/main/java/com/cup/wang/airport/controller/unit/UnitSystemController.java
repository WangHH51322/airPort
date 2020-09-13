package com.cup.wang.airport.controller.unit;

import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.UnitSystem;
import com.cup.wang.airport.service.UnitSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/9 15:21
 */
@RestController
@RequestMapping("/unit/unitSystem")
public class UnitSystemController {

    @Autowired
    UnitSystemService unitSystemService;

    @GetMapping("/")
    public List<UnitSystem> getAllUnitSystems(){
        return unitSystemService.getAll();
    }

    @GetMapping("/{id}")
    public UnitSystem getUnitSystemById(@PathVariable Integer id){
        return unitSystemService.getById(id);
    }

    @PostMapping("/")
    public RespBean addUnitSystem(@RequestBody UnitSystem unitSystem){
        if (unitSystemService.addUnitSystem(unitSystem) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateUnitSystem(@RequestBody UnitSystem unitSystem){
        if (unitSystemService.updateUnitSystem(unitSystem) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteUnitSystem(@PathVariable Integer id){
        if (unitSystemService.deleteUnitSystem(id) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
