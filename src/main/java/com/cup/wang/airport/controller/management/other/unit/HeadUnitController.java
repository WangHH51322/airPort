package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.HeadUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.HeadUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/27 19:56
 */
@RestController
@RequestMapping("/management/others/head/unit")
public class HeadUnitController {

    @Autowired
    HeadUnitService headUnitService;

    @GetMapping("/")
    public List<HeadUnit> getAllHeadUnits(){
        return headUnitService.getAllHeadUnits();
    }

    @GetMapping("/{id}")
    public HeadUnit getHeadUnitById(@PathVariable Integer id){
        return headUnitService.getHeadUnitById(id);
    }

    @PostMapping("/")
    public RespBean addHeadUnit(@RequestBody HeadUnit headUnit){
        if (headUnitService.addHeadUnit(headUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @PutMapping("/")
    public RespBean updateHeadUnit(@RequestBody HeadUnit headUnit){
        if (headUnitService.updateHeadUnit(headUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteHeadUnitById(@PathVariable Integer id){
        if (headUnitService.deleteHeadUnitById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
