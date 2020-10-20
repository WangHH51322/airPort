package com.cup.wang.airport.controller.management.liquid.viscosity;

import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.model.viscosity.ViscosityConstant;
import com.cup.wang.airport.service.ViscosityConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/26 10:14
 */
@RestController
@RequestMapping("/management/liquid/viscosity/constant")
public class ViscosityConstantController {

    @Autowired
    ViscosityConstantService viscosityConstantService;

    @GetMapping("/")
    public List<ViscosityConstant> getAllViscosityConstants(){
        return viscosityConstantService.getAllViscosityConstants();
    }

    //根据density_id获取一整组的数据
    @GetMapping("/{did}")
    public ViscosityConstant getViscosityConstantByViscosityId(@PathVariable Integer did){
        return viscosityConstantService.getViscosityConstantByViscosityId(did);
    }

    @PutMapping("/")
    public RespBean updateViscosityConstant(@RequestBody ViscosityConstant viscosityConstant){
        if (viscosityConstantService.updateViscosityConstant(viscosityConstant) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    //根据粘度的id创建一行数据,viscosityId=did,id自增并返回
    @PostMapping("/{did}")
    public RespBean addViscosityConstantByDensityId(@PathVariable Integer did){
        ViscosityConstant viscosityConstant = new ViscosityConstant();
        viscosityConstant.setViscosityId(did);
        if (viscosityConstantService.addViscosityConstantByDensityId(viscosityConstant) == 1){
            Integer id = viscosityConstant.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteViscosityConstantById(@PathVariable Integer id){
        if (viscosityConstantService.deleteViscosityConstantById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return  RespBean.error("删除失败!");
    }
}
