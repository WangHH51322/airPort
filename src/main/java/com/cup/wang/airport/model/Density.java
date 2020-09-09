package com.cup.wang.airport.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:54
 */
public class Density implements Serializable {
    private Integer id;
    private Integer unit;
    private Integer liquidId;
    private Double densityConstant;
    private Double densityResult;
    private Double temperatureResult;

    private DensityUnit densityUnit;
    private List<DensityFitting> densityFittingList;
    private DensityPolynomial densityPolynomial;
    private DensityRelative densityRelative;

    public Integer getLiquidId() {
        return liquidId;
    }

    public void setLiquidId(Integer liquidId) {
        this.liquidId = liquidId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getDensityConstant() {
        return densityConstant;
    }

    public void setDensityConstant(Double densityConstant) {
        this.densityConstant = densityConstant;
    }

    public Double getDensityResult() {
        return densityResult;
    }

    public void setDensityResult(Double densityResult) {
        this.densityResult = densityResult;
    }

    public Double getTemperatureResult() {
        return temperatureResult;
    }

    public void setTemperatureResult(Double temperatureResult) {
        this.temperatureResult = temperatureResult;
    }

    public DensityUnit getDensityUnit() {
        return densityUnit;
    }

    public void setDensityUnit(DensityUnit densityUnit) {
        this.densityUnit = densityUnit;
    }

    public List<DensityFitting> getDensityFittingList() {
        return densityFittingList;
    }

    public void setDensityFittingList(List<DensityFitting> densityFittingList) {
        this.densityFittingList = densityFittingList;
    }

    public DensityPolynomial getDensityPolynomial() {
        return densityPolynomial;
    }

    public void setDensityPolynomial(DensityPolynomial densityPolynomial) {
        this.densityPolynomial = densityPolynomial;
    }

    public DensityRelative getDensityRelative() {
        return densityRelative;
    }

    public void setDensityRelative(DensityRelative densityRelative) {
        this.densityRelative = densityRelative;
    }
}

