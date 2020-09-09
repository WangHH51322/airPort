package com.cup.wang.airport.controller.demo;

import com.cup.wang.airport.mapper.DemoMapper;
import com.cup.wang.airport.model.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/30 18:38
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoMapper demoMapper;

    @GetMapping("/{id}")
    public Demo getDemoById(@PathVariable Integer id){
        return demoMapper.getAllDemos(id);
    }
}
