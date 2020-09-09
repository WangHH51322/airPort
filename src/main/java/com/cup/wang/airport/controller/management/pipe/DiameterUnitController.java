package com.cup.wang.airport.controller.management.pipe;

import com.cup.wang.airport.model.DiameterUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.TemperatureUnit;
import com.cup.wang.airport.service.DiameterUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:06
 */
@RestController
@RequestMapping("/management/pipe/diameter/unit")
public class DiameterUnitController {

    @Autowired
    DiameterUnitService diameterUnitService;

    @GetMapping("/")
    public List<DiameterUnit> getAllDiameterUnit(){
        return diameterUnitService.getAllDiameterUnit();
    }

    @GetMapping("/{id}")
    public Optional<DiameterUnit> getDiameterUnitById(@PathVariable Integer id){
        return diameterUnitService.getTemperatureUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateDiameterUnit(@RequestBody DiameterUnit diameterUnit){
        if (diameterUnitService.updateDiameterUnit(diameterUnit) != null){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/")
    public RespBean addDiameterUnit(@RequestBody DiameterUnit diameterUnit){
        if (diameterUnitService.addDiameterUnit(diameterUnit) != null){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDiameterUnitById(@PathVariable Integer id){

        try {
            diameterUnitService.deleteDiameterUnitById(id);
            return RespBean.ok("删除成功!");
        } catch (Exception e) {
            return RespBean.error("删除失败!");
        }
    }
}
