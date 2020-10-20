package com.cup.wang.airport.controller.management.valve;

import com.cup.wang.airport.model.Valve;
import com.cup.wang.airport.service.ValveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/4 15:12
 */
@RestController
@RequestMapping("/management/valve")
public class ValveController {

    @Autowired
    ValveService valveService;

    @GetMapping("/{id}")
    public List<Valve> getValvesByProjectId(@PathVariable Integer id){
        return valveService.getValvesByProjectId(id);
    }
}
