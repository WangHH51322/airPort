package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.VaporConstantMapper;
import com.cup.wang.airport.model.vapor.VaporConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 15:31
 */
@Service
public class VaporConstantService {

    @Autowired
    VaporConstantMapper vaporConstantMapper;

    public List<VaporConstant> getAllVaporConstants() {
        return vaporConstantMapper.getAllVaporConstants();
    }

    public VaporConstant getVaporConstantByVaporId(Integer did) {
        return vaporConstantMapper.getVaporConstantByVaporId(did);
    }

    public int updateVaporConstant(VaporConstant vaporConstant) {
        return vaporConstantMapper.updateByPrimaryKeySelective(vaporConstant);
    }

    public int addVaporConstantByVaporId(VaporConstant vaporConstant) {
        return vaporConstantMapper.insertSelective(vaporConstant);
    }

    public int deleteVaporConstantById(Integer id) {
        return vaporConstantMapper.deleteByPrimaryKey(id);
    }
}
