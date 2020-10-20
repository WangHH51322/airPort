package com.cup.wang.airport.controller.management.node;

import com.cup.wang.airport.model.Node;
import com.cup.wang.airport.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/30 17:01
 */
@RestController
@RequestMapping("/management/node")
public class NodeController {

    @Autowired
    NodeService nodeService;

    @GetMapping("/{id}")
    public List<Node> getNodesByProjectId(@PathVariable Integer id){
        return nodeService.getNodesByProjectId(id);
    }
}
