package com.cup.wang.airport.controller.unit;

import com.cup.wang.airport.model.PhysicalQuantityType;
import com.cup.wang.airport.service.PhysicalQuantityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/13 20:40
 */
@RestController
@RequestMapping("/unit/physicalQuantityType")
public class PhysicalQuantityTypeController {

    @Autowired
    PhysicalQuantityTypeService physicalQuantityTypeService;

    @GetMapping("/")
    public List<PhysicalQuantityType> getAllPhysicalQuantityTypes(){
        return physicalQuantityTypeService.getAllPhysicalQuantityTypes();
    }
}
