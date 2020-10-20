package com.cup.wang.airport.mapper;

import com.cup.wang.airport.model.density.DensityPolynomial;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 21:06
 */
public interface DensityPolynomialMapper {
    List<DensityPolynomial> getAllDensityPolynomials();

    DensityPolynomial getDensityPolynomialById(Integer id);

    Integer updateDensityPolynomial(DensityPolynomial densityPolynomial);

    Integer addDensityPolynomial(DensityPolynomial densityPolynomial);

    Integer deleteDensityPolynomialById(Integer id);
}
