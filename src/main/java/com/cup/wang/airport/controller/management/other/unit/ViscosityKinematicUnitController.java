package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.ViscosityKinematicUnit;
import com.cup.wang.airport.service.ViscosityKinematicUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:37
 */
@RestController
@RequestMapping("/management/others/viscositykinematic/unit")
public class ViscosityKinematicUnitController {

    @Autowired
    ViscosityKinematicUnitService viscosityKinematicUnitService;

    @GetMapping("/")
    public List<ViscosityKinematicUnit> getAllViscosityKinematicUnits(){
        return viscosityKinematicUnitService.getAllHeadUnitViscosityKinematicUnits();
    }

    @GetMapping("/{id}")
    public ViscosityKinematicUnit getViscosityKinematicUnitById(@PathVariable Integer id){
        return viscosityKinematicUnitService.getViscosityKinematicUnitById(id);
    }

    @PostMapping("/")
    public RespBean addViscosityKinematicUnit(@RequestBody ViscosityKinematicUnit viscosityKinematicUnit){
        if (viscosityKinematicUnitService.addViscosityKinematicUnit(viscosityKinematicUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateViscosityKinematicUnit(@RequestBody ViscosityKinematicUnit viscosityKinematicUnit){
        if (viscosityKinematicUnitService.updateViscosityKinematicUnit(viscosityKinematicUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteViscosityKinematicUnitById(@PathVariable Integer id){
        if (viscosityKinematicUnitService.deleteViscosityKinematicUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
