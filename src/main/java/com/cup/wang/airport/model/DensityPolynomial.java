package com.cup.wang.airport.model;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:55
 */
public class DensityPolynomial implements Serializable {
    private Integer id;
    private Integer densityId;
    private Double x0;
    private Double x1;
    private Double x2;
    private Double x3;
    private Double x4;
    private Double x5;

    @Override
    public String toString() {
        return "DensityPolynomial{" +
                "id=" + id +
                ", densityId=" + densityId +
                ", x0=" + x0 +
                ", x1=" + x1 +
                ", x2=" + x2 +
                ", x3=" + x3 +
                ", x4=" + x4 +
                ", x5=" + x5 +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDensityId() {
        return densityId;
    }

    public void setDensityId(Integer densityId) {
        this.densityId = densityId;
    }

    public Double getX0() {
        return x0;
    }

    public void setX0(Double x0) {
        this.x0 = x0;
    }

    public Double getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }

    public Double getX2() {
        return x2;
    }

    public void setX2(Double x2) {
        this.x2 = x2;
    }

    public Double getX3() {
        return x3;
    }

    public void setX3(Double x3) {
        this.x3 = x3;
    }

    public Double getX4() {
        return x4;
    }

    public void setX4(Double x4) {
        this.x4 = x4;
    }

    public Double getX5() {
        return x5;
    }

    public void setX5(Double x5) {
        this.x5 = x5;
    }
}

