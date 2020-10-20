package com.cup.wang.airport.controller.management.liquid.viscosity;

import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.model.viscosity.ViscosityFitting;
import com.cup.wang.airport.service.ViscosityFittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/26 10:53
 */
@RestController
@RequestMapping("/management/liquid/viscosity/fitting")
public class ViscosityFittingController {

    @Autowired
    ViscosityFittingService viscosityFittingService;

    @GetMapping("/")
    public List<ViscosityFitting> getAllViscosityFittings(){
        return viscosityFittingService.getAllViscosityFittings();
    }

    //根据viscosity_id获取一整组的数据
    @GetMapping("/{did}")
    public List<ViscosityFitting> getViscosityFittingsByViscosityId(@PathVariable Integer did){
        return viscosityFittingService.getViscosityFittingsByViscosityId(did);
    }

    @PutMapping("/")
    public RespBean updateViscosityFittings(@RequestBody List<ViscosityFitting> viscosityFittings){
        if (viscosityFittingService.updateViscosityFittings(viscosityFittings) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /*前端在传入多个ViscosityFitting对象时,需要提前将viscosity_id给赋值*/
    @PostMapping("/")
    public RespBean addViscosityFittingsByViscosityId(@RequestBody List<ViscosityFitting> viscosityFittings){
        if(viscosityFittingService.addViscosityFittingsByViscosityId(viscosityFittings) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
}
