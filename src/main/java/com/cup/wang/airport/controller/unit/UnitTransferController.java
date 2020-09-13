package com.cup.wang.airport.controller.unit;

import com.cup.wang.airport.model.UnitTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/12 14:16
 */
@RestController
@RequestMapping("/unit/unitTransfer")
public class UnitTransferController {

    @Autowired
    UnitTransfer unitTransfer;

    @GetMapping("/")
    public UnitTransfer getUnitTransferValue(Integer quantityUnitIdA,Integer quantityUnitIdB,
                                       @RequestParam(defaultValue = "0") Double quantityUnitValueB){

        UnitTransfer unitTransfer1 = new UnitTransfer();
        double quantityUnitValueA = unitTransfer.transfer(quantityUnitIdA,quantityUnitIdB,quantityUnitValueB);

        unitTransfer1.setQuantityUnitValueA(quantityUnitValueA);
        unitTransfer1.setQuantityUnitValueB(quantityUnitValueB);
        unitTransfer1.setQuantityUnitIdA(quantityUnitIdA);
        unitTransfer1.setQuantityUnitIdB(quantityUnitIdB);

        return unitTransfer1;
    }
}
