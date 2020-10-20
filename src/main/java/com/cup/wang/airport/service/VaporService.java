package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.VaporMapper;
import com.cup.wang.airport.model.vapor.Vapor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 16:26
 */
@Service
public class VaporService {

    @Autowired
    VaporMapper vaporMapper;

    public Vapor getVaporByLiquidId(Integer id) {
        Integer vaporValueId = vaporMapper.selectByPrimaryKey(id).getVaporValueId();
        return vaporMapper.getVaporByLiquidId(id,vaporValueId).get(0);
    }

    public int updateVapor(Vapor vapor) {
        return vaporMapper.updateByPrimaryKeySelective(vapor);
    }
}
