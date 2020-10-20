package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.ValveMapper;
import com.cup.wang.airport.model.Valve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/4 15:13
 */
@Service
public class ValveService {

    @Autowired
    ValveMapper valveMapper;

    public List<Valve> getValvesByProjectId(Integer id) {
        return valveMapper.getValvesByProjectId(id);
    }
}
