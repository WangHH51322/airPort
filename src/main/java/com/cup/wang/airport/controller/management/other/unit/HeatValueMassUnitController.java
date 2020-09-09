package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeatValueMassUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.HeatValueMassUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:43
 */
@RestController
@RequestMapping("/management/others/heatvaluemass/unit")
public class HeatValueMassUnitController {

    @Autowired
    HeatValueMassUnitService heatValueMassUnitService;

    @GetMapping("/")
    public List<HeatValueMassUnit> getAllHeatValueMassUnits(){
        return heatValueMassUnitService.getAllHeatValueMassUnits();
    }

    @GetMapping("/{id}")
    public HeatValueMassUnit getHeatValueMassUnitById(@PathVariable Integer id){
        return heatValueMassUnitService.getHeatValueMassUnitById(id);
    }

    @PostMapping("/")
    public RespBean addHeatValueMassUnit(@RequestBody HeatValueMassUnit heatValueMassUnit){
        if (heatValueMassUnitService.addHeatValueMassUnit(heatValueMassUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateHeatValueMassUnit(@RequestBody HeatValueMassUnit heatValueMassUnit){
        if (heatValueMassUnitService.updateHeatValueMassUnit(heatValueMassUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHeatValueMassUnitById(@PathVariable Integer id){
        if (heatValueMassUnitService.deleteHeatValueMassUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
