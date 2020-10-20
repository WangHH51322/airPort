package com.cup.wang.airport.controller.management.liquid.vapor;

import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.model.vapor.VaporFitting;
import com.cup.wang.airport.service.VaporFittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 16:00
 */
@RestController
@RequestMapping("/management/liquid/vapor/fitting")
public class VaporFittingController {

    @Autowired
    VaporFittingService vaporFittingService;

    @GetMapping("/")
    public List<VaporFitting> getAllVaporFittings(){
        return vaporFittingService.getAllVaporFittings();
    }

    //根据vapor_id获取一整组的数据
    @GetMapping("/{did}")
    public List<VaporFitting> getVaporFittingsByVaporId(@PathVariable Integer did){
        return vaporFittingService.getVaporFittingsByVaporId(did);
    }

    @PutMapping("/")
    public RespBean updateVaporFittings(@RequestBody List<VaporFitting> vaporFittings){
        if (vaporFittingService.updateVaporFittings(vaporFittings) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    /*前端在传入多个VaporFitting对象时,需要提前将vapor_id给赋值*/
    @PostMapping("/")
    public RespBean addVaporFittingsByVaporId(@RequestBody List<VaporFitting> vaporFittings){
        if(vaporFittingService.addVaporFittingsByVaporId(vaporFittings) == 1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

}
