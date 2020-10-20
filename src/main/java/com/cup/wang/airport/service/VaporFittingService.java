package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.VaporFittingMapper;
import com.cup.wang.airport.model.vapor.VaporFitting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/27 15:58
 */
@Service
public class VaporFittingService {

    @Autowired
    VaporFittingMapper vaporFittingMapper;

    public List<VaporFitting> getAllVaporFittings() {
        return vaporFittingMapper.getAllVaporFittings();
    }

    public List<VaporFitting> getVaporFittingsByVaporId(Integer did) {
        return vaporFittingMapper.getVaporFittingsByVaporId(did);
    }


    public int updateVaporFittings(List<VaporFitting> vaporFittings) {
        return vaporFittingMapper.updateVaporFittings(vaporFittings);
    }

    public int addVaporFittingsByVaporId(List<VaporFitting> vaporFittings) {
        return vaporFittingMapper.addVaporFittingsByVaporId(vaporFittings);
    }
}
