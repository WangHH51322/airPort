package com.cup.wang.airport.controller.management.pipe;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.WallThicknessUnit;
import com.cup.wang.airport.service.WallThicknessUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/28 17:06
 */
@RestController
@RequestMapping("/management/pipe/wallthickness/unit")
public class WallThicknessUnitController {

    @Autowired
    WallThicknessUnitService wallThicknessUnitService;

    @GetMapping("/")
    public List<WallThicknessUnit> getAllWallThicknessUnits(){
        return wallThicknessUnitService.getAllWallThicknessUnits();
    }

    @GetMapping("/{id}")
    public WallThicknessUnit getWallThicknessUnitById(@PathVariable Integer id){
        return wallThicknessUnitService.getWallThicknessUnitById(id);
    }

    @PostMapping("/")
    public RespBean addWallThicknessUnit(@RequestBody WallThicknessUnit wallThicknessUnit){
        if (wallThicknessUnitService.addWallThicknessUnit(wallThicknessUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateWallThicknessUnit(@RequestBody WallThicknessUnit wallThicknessUnit){
        if (wallThicknessUnitService.updateWallThicknessUnit(wallThicknessUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteWallThicknessUnitById(@PathVariable Integer id){
        if (wallThicknessUnitService.deleteWallThicknessUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
