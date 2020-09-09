package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.Density;
import com.cup.wang.airport.service.DensityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:59
 */
@RestController
@RequestMapping("/management/liquid/density")
public class DensityController {

    @Autowired
    DensityService densityService;

    @GetMapping("/")
    public List<Density> getAllDensities(){
        return densityService.getAllDensities();
    }

    @GetMapping("/{id}")
    public Density getDensityById(@PathVariable Integer id){
        return densityService.getDensityById(id);
    }


}
