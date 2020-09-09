package com.cup.wang.airport.controller.management.pipe;

import com.cup.wang.airport.model.LengthUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.TemperatureUnit;
import com.cup.wang.airport.service.LengthUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/21 16:45
 */
@RestController
@RequestMapping("/management/pipe/length/unit")
public class LengthUnitController {

    @Autowired
    LengthUnitService lengthUnitService;

    @GetMapping("/")
    public List<LengthUnit> getAllLengthUnit(){
        return lengthUnitService.getAllLengthUnit();
    }

    @GetMapping("/{id}")
    public Optional<LengthUnit> getLengthUnitById(@PathVariable Integer id){
        return lengthUnitService.getLengthUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateLengthUnit(@RequestBody LengthUnit lengthUnit){
        if (lengthUnitService.updateLengthUnit(lengthUnit) != null){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/")
    public RespBean addLengthUnit(@RequestBody LengthUnit lengthUnit){
        if (lengthUnitService.addLengthUnit(lengthUnit) != null){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteLengthUnitById(@PathVariable Integer id){

        try {
            lengthUnitService.deleteLengthUnitById(id);
            return RespBean.ok("删除成功!");
        } catch (Exception e) {
            return RespBean.error("删除失败!");
        }
    }
}
