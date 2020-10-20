package com.cup.wang.airport.controller.management.liquid;

import com.cup.wang.airport.model.Liquid;
import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.service.LiquidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:58
 */
@RestController
@RequestMapping("/management/liquid")
public class LiquidController {

    @Autowired
    LiquidService liquidService;

    @GetMapping("/")
    public List<Liquid> getAllLiquids(){
        return liquidService.getAllLiquids();
    }

    @PutMapping("/")
    public RespBean updateLiquidById(@RequestBody Liquid liquid){
        if (liquidService.updateLiquidById(liquid) == 1){
            return RespBean.ok("更新成功!!!");
        }
        return RespBean.error("更新失败!!!");
    }

    @PostMapping("/")
    public RespBean addLiquid(@RequestBody Liquid liquid){
        if (liquidService.addLiquid(liquid) == 1){
            Integer id = liquid.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

}
