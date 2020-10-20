package com.cup.wang.airport.controller.management.pipe;

import com.cup.wang.airport.model.Pipe;
import com.cup.wang.airport.service.PipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/30 10:46
 */
@RestController
@RequestMapping("/management/pipe")
public class PipeController {

    @Autowired
    PipeService pipeService;

    @GetMapping("/{id}")
    public List<Pipe> getPipesByProjectId(@PathVariable Integer id){
        return pipeService.getPipesByProjectId(id);
    }

}
