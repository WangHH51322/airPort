package com.cup.wang.airport.model.density;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:55
 */
public class DensityRelative implements Serializable {
    private Integer id;
    private Integer densityId;
    private Double x1;
    private Double x2;

    @Override
    public String toString() {
        return "DensityRelative{" +
                "id=" + id +
                ", densityId=" + densityId +
                ", x1=" + x1 +
                ", x2=" + x2 +
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
}

