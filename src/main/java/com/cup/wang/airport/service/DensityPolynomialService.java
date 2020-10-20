package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.DensityPolynomialMapper;
import com.cup.wang.airport.model.density.DensityPolynomial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:05
 */
@Service
public class DensityPolynomialService {

    @Autowired
    DensityPolynomialMapper densityPolynomialMapper;

    public List<DensityPolynomial> getAllDensityPolynomials() {
        return densityPolynomialMapper.getAllDensityPolynomials();
    }

    public DensityPolynomial getDensityPolynomialById(Integer id) {
        return densityPolynomialMapper.getDensityPolynomialById(id);
    }

    public Integer updateDensityPolynomial(DensityPolynomial densityPolynomial) {
        return densityPolynomialMapper.updateDensityPolynomial(densityPolynomial);
    }

    public Integer addDensityPolynomial(DensityPolynomial densityPolynomial) {
        return densityPolynomialMapper.addDensityPolynomial(densityPolynomial);
    }

    public Integer deleteDensityPolynomialById(Integer id) {
        return densityPolynomialMapper.deleteDensityPolynomialById(id);
    }
}

