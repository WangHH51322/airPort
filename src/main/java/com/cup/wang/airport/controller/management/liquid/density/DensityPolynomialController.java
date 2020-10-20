package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.density.DensityPolynomial;
import com.cup.wang.airport.model.utils.RespBean;
import com.cup.wang.airport.service.DensityPolynomialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:05
 */
@RestController
@RequestMapping("/management/liquid/density/polynomial")
public class DensityPolynomialController {

    @Autowired
    DensityPolynomialService densityPolynomialService;

    @GetMapping("/")
    public List<DensityPolynomial> getAllDensityPolynomials(){
        return densityPolynomialService.getAllDensityPolynomials();
    }

    @GetMapping("/{id}")
    public DensityPolynomial getDensityPolynomialById(@PathVariable Integer id){
        return densityPolynomialService.getDensityPolynomialById(id);
    }

    @PutMapping("/")
    public RespBean updateDensityPolynomial(@RequestBody DensityPolynomial densityPolynomial){
        if (densityPolynomialService.updateDensityPolynomial(densityPolynomial) == 1){
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @PostMapping("/")
    public RespBean addDensityPolynomial(@RequestBody DensityPolynomial densityPolynomial){
        if (densityPolynomialService.addDensityPolynomial(densityPolynomial) == 1){
            Integer id = densityPolynomial.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    //根据密度的id创建一行数据,densityId=did,id自增并返回
    @PostMapping("/{did}")
    public RespBean addDensityPolynomialByDensityId(@PathVariable Integer did){
        DensityPolynomial densityPolynomial = new DensityPolynomial();
        densityPolynomial.setDensityId(did);
        if (densityPolynomialService.addDensityPolynomial(densityPolynomial) == 1){
            Integer id = densityPolynomial.getId();
            return RespBean.ok("添加成功!",id);
        }
        return RespBean.error("添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDensityPolynomialById(@PathVariable Integer id){
        if (densityPolynomialService.deleteDensityPolynomialById(id) == 1){
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}

