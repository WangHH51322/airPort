package com.cup.wang.airport.controller.management.liquid.viscosity;

import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.model.viscosity.Viscosity;
import com.cup.wang.airport.service.ViscosityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 10:14
 */
@RestController
@RequestMapping("/management/liquid/viscosity")
public class ViscosityController {

    @Autowired
    ViscosityService viscosityService;

    @GetMapping("/{id}")
    public Viscosity getViscosityByLiquidId(@PathVariable Integer id){
        return viscosityService.getViscosityByLiquidId(id);
    }

    @PutMapping("/")
    public RespBean updateViscosity(@RequestBody Viscosity viscosity){
        if (viscosityService.updateViscosity(viscosity) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!!!");
    }
}
