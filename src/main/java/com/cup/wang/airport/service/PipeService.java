package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.PipeMapper;
import com.cup.wang.airport.model.Pipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/30 10:45
 */
@Service
public class PipeService {

    @Autowired
    PipeMapper pipeMapper;

    public List<Pipe> getPipesByProjectId(Integer id) {
        return pipeMapper.getPipesByProjectId(id);
    }
}
