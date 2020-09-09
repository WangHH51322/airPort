package com.cup.wang.airport.model;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 20:56
 */
public class DensityUnit implements Serializable {

    private Integer id;
    private String unit;

    @Override
    public String toString() {
        return "DensityUnit{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

