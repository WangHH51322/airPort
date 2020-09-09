package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.HeatTransferCoeffUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.HeatTransferCoeffUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 22:28
 */
@RestController
@RequestMapping("/management/others/heattransfercoeff/unit")
public class HeatTransferCoeffUnitController {

    @Autowired
    HeatTransferCoeffUnitService heatTransferCoeffUnitService;

    @GetMapping("/")
    public List<HeatTransferCoeffUnit> getAllHeatTransferCoeffUnits(){
        return heatTransferCoeffUnitService.getAllHeatTransferCoeffUnits();
    }

    @GetMapping("/{id}")
    public HeatTransferCoeffUnit getHeatTransferCoeffUnitById(@PathVariable Integer id){
        return heatTransferCoeffUnitService.getHeatTransferCoeffUnitById(id);
    }

    @PostMapping("/")
    public RespBean addHeatTransferCoeffUnit(@RequestBody HeatTransferCoeffUnit heatTransferCoeffUnit){
        if (heatTransferCoeffUnitService.addHeaddHeatTransferCoeffUnit(heatTransferCoeffUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateHeatTransferCoeffUnit(@RequestBody HeatTransferCoeffUnit heatTransferCoeffUnit){
        if (heatTransferCoeffUnitService.updateHeatTransferCoeffUnit(heatTransferCoeffUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHeatTransferCoeffUnitById(@PathVariable Integer id){
        if (heatTransferCoeffUnitService.deleteHeatTransferCoeffUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
