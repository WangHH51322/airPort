package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityFittingMapper;
import com.cup.wang.airport.model.DensityFitting;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:02
 */
@Transactional
@Service
public class DensityFittingService {

    @Autowired
    DensityFittingMapper densityFittingMapper;

    public List<DensityFitting> getAllDensityFittings() {
        return densityFittingMapper.getAllDensityFittings();
    }

    public List<DensityFitting> getDensityFittingsByDensityId(Integer did) {
        return densityFittingMapper.getDensityFittingsByDensityId(did);
    }

    public Integer updateDensityFitting(DensityFitting densityFitting) {
        return densityFittingMapper.updateDensityFitting(densityFitting);
    }


    public Integer addDensityFitting(DensityFitting densityFitting) {
        try {
            Integer integer = densityFittingMapper.addDensityFitting(densityFitting);
            return integer;
        } catch (Exception e1) {
            return 0;
        } finally {
            return 0;
        }
    }

    public Integer deleteDensityFittingById(Integer id) {
        return densityFittingMapper.deleteDensityFittingById(id);
    }
}

