package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.NodeMapper;
import com.cup.wang.airport.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/30 17:00
 */
@Service
public class NodeService {

    @Autowired
    NodeMapper nodeMapper;

    public List<Node> getNodesByProjectId(Integer id) {
        return nodeMapper.getNodesByProjectId(id);
    }
}
