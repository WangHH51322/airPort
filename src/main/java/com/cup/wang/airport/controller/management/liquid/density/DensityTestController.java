package com.cup.wang.airport.controller.management.liquid.density;

import com.cup.wang.airport.model.Density;
import com.cup.wang.airport.model.DensityTest;
import com.cup.wang.airport.service.DensityTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/3 17:45
 */
@RestController
@RequestMapping("/management/liquid/density/test")
public class DensityTestController {

    @Autowired
    DensityTestService densityTestService;

    @GetMapping("/{id}")
    public DensityTest getDensityById(@PathVariable Integer id){
        return densityTestService.getDensityById(id);
    }
}
