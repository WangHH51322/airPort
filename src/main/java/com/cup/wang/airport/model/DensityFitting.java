package com.cup.wang.airport.model;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:55
 */
public class DensityFitting implements Serializable {
    private Integer id;
    private Integer densityId;
    private Double density;
    private Double temperature;

    @Override
    public String toString() {
        return "DensityFitting{" +
                "id=" + id +
                ", densityId=" + densityId +
                ", density=" + density +
                ", temperature=" + temperature +
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

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}

