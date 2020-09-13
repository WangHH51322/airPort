package com.cup.wang.airport.controller.unit;

import com.cup.wang.airport.model.PhysicalQuantity;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.PhysicalQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/9 15:45
 */
@RestController
@RequestMapping("/unit/physicalQuantity")
public class PhysicalQuantityController {

    @Autowired
    PhysicalQuantityService physicalQuantityService;

    @GetMapping("/")
    public List<PhysicalQuantity> getAllPhysicalQuantity(){
        return physicalQuantityService.getAllPhysicalQuantity();
    }

    @GetMapping("/simple/")
    public List<PhysicalQuantity> getAllPhysicalQuantitySimple(){
        return physicalQuantityService.getAllPhysicalQuantitySimple();
    }

    @GetMapping("/{id}")
    public PhysicalQuantity getPhysicalQuantityById(@PathVariable Integer id){
        return physicalQuantityService.getPhysicalQuantityById(id);
    }

    @GetMapping("/simple/{id}")
    public PhysicalQuantity getSimplePhysicalQuantityById(@PathVariable Integer id){
        return physicalQuantityService.getSimplePhysicalQuantityById(id);
    }

    @PutMapping("/")
    public RespBean updatePhysicalQuantity(@RequestBody PhysicalQuantity physicalQuantity){
        if (physicalQuantityService.updatePhysicalQuantity(physicalQuantity) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/")
    public RespBean addPhysicalQuantity(@RequestBody PhysicalQuantity physicalQuantity){
        if (physicalQuantityService.addPhysicalQuantity(physicalQuantity) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePhysicalQuantity(@PathVariable Integer id){
        if (physicalQuantityService.deletePhysicalQuantity(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
