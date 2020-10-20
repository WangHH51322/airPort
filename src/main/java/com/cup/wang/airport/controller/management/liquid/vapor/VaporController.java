package com.cup.wang.airport.controller.management.liquid.vapor;

import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.model.vapor.Vapor;
import com.cup.wang.airport.service.VaporService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 16:27
 */
@RestController
@RequestMapping("/management/liquid/vapor")
public class VaporController {

    @Autowired
    VaporService vaporService;

    /*根据liquid_id查询*/
    @GetMapping("/{id}")
    public Vapor getVaporByLiquidId(@PathVariable Integer id){
        return vaporService.getVaporByLiquidId(id);
    }

    @PutMapping("/")
    public RespBean updateVapor(@RequestBody Vapor vapor){
        if (vaporService.updateVapor(vapor) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!!!");
    }
}
