package com.cup.wang.airport.controller.unit;

import com.cup.wang.airport.model.QuantityUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.QuantityUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/9 16:16
 */
@RestController
@RequestMapping("/unit/quantityUnit")
public class QuantityUnitController {

    @Autowired
    QuantityUnitService quantityUnitService;

    @GetMapping("/")
    public List<QuantityUnit> getAllQuantityUnit(){
        return quantityUnitService.getAllQuantityUnit();
    }

    @GetMapping("/{id}")
    public QuantityUnit getQuantityUnitById(@PathVariable Integer id){
        return quantityUnitService.getQuantityUnitById(id);
    }

    @GetMapping("/physicalQuantity/{id}")
    public List<QuantityUnit> getQuantityUnitByPhysicalQuantityId(@PathVariable Integer id){
        return quantityUnitService.getQuantityUnitByPhysicalQuantityId(id);
    }

    @GetMapping("/physicalQuantity/")
    public List<QuantityUnit> getQuantityUnitByPhysicalQuantityIdAndUnitSystemId(Integer id1,Integer id2)
    {
        return quantityUnitService.getQuantityUnitByPhysicalQuantityIdAndUnitSystemId(id1,id2);
    }

    @PutMapping("/")
    public RespBean updateQuantityUnit(@RequestBody QuantityUnit quantityUnit){
        if (quantityUnitService.updateQuantityUnit(quantityUnit) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/")
    public RespBean addQuantityUnit(@RequestBody QuantityUnit quantityUnit){
        if (quantityUnitService.addQuantityUnit(quantityUnit) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteQuantityUnit(@PathVariable Integer id){
        if (quantityUnitService.deleteQuantityUnit(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
