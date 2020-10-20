package com.cup.wang.airport.controller.management.liquid.vapor;

import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.model.vapor.VaporConstant;
import com.cup.wang.airport.service.VaporConstantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 15:32
 */
@RestController
@RequestMapping("/management/liquid/vapor/constant")
public class VaporConstantController {

    @Autowired
    VaporConstantService vaporConstantService;

    @GetMapping("/")
    public List<VaporConstant> getAllVaporConstants(){
        return vaporConstantService.getAllVaporConstants();
    }

    //根据vapor_id获取一整组的数据
    @GetMapping("/{did}")
    public VaporConstant getVaporConstantByVaporId(@PathVariable Integer did){
        return vaporConstantService.getVaporConstantByVaporId(did);
    }

    @PutMapping("/")
    public RespBean updateVaporConstant(@RequestBody VaporConstant vaporConstant){
        if (vaporConstantService.updateVaporConstant(vaporConstant) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    //根据蒸气压的id创建一行数据,vaporId=did,id自增并返回
    @PostMapping("/{did}")
    public RespBean addVaporConstantByVaporId(@PathVariable Integer did){
        VaporConstant vaporConstant = new VaporConstant();
        vaporConstant.setVaporId(did);
        if (vaporConstantService.addVaporConstantByVaporId(vaporConstant) == 1){
            Integer id = vaporConstant.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteVaporConstantById(@PathVariable Integer id){
        if (vaporConstantService.deleteVaporConstantById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return  RespBean.error("删除失败!");
    }
}
