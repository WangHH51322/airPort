package com.cup.wang.airport.controller.management.pipe;

import com.cup.wang.airport.model.ElevationUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.ElevationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:26
 */
@RestController
@RequestMapping("/management/pipe/elevation/unit")
public class ElevationUnitController {

    @Autowired
    ElevationUnitService elevationUnitService;

    @GetMapping("/")
    public List<ElevationUnit> getAllElevationUnit(){
        return elevationUnitService.getAllElevationUnit();
    }

    @GetMapping("/{id}")
    public Optional<ElevationUnit> getElevationUnitById(@PathVariable Integer id){
        return elevationUnitService.getElevationUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateElevationUnit(@RequestBody ElevationUnit elevationUnit){
        if (elevationUnitService.updateElevationUnit(elevationUnit) != null){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/")
    public RespBean addElevationUnit(@RequestBody ElevationUnit elevationUnit){
        if (elevationUnitService.addElevationUnit(elevationUnit) != null){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteElevationUnitById(@PathVariable Integer id){

        try {
            elevationUnitService.deleteElevationUnitById(id);
            return RespBean.ok("删除成功!");
        } catch (Exception e) {
            return RespBean.error("删除失败!");
        }
    }
}
