package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.DensityConstant;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.service.DensityConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/21 15:35
 */
@RestController
@RequestMapping("/management/liquid/density/constant")
public class DensityConstantController {

    @Autowired
    DensityConstantService densityConstantService;

    @GetMapping("/")
    public List<DensityConstant> getAllDensityConstants(){
        return densityConstantService.getAllDensityConstants();
    }

    //根据density_id获取一整组的数据
    @GetMapping("/{did}")
    public DensityConstant getDensityConstantByDensityId(@PathVariable Integer did){
        return densityConstantService.getDensityConstantByDensityId(did);
    }

    @PutMapping("/")
    public RespBean updateDensityConstant(@RequestBody DensityConstant densityConstant){
        if (densityConstantService.updateDensityConstant(densityConstant) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    //根据密度的id创建一行数据,densityId=did,id自增并返回
    @PostMapping("/{did}")
    public RespBean addDensityConstantByDensityId(@PathVariable Integer did){
        DensityConstant densityConstant = new DensityConstant();
        densityConstant.setDensityId(did);
        if (densityConstantService.addDensityConstantByDensityId(densityConstant) == 1){
            Integer id = densityConstant.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }


}
