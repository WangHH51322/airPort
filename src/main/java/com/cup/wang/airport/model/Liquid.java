package com.cup.wang.airport.model;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 22:51
 */
public class Liquid implements Serializable {
    private Integer id;
    private String name;
    private Integer densityId;
    private Density density;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDensityId() {
        return densityId;
    }

    public void setDensityId(Integer densityId) {
        this.densityId = densityId;
    }

    public Density getDensity() {
        return density;
    }

    public void setDensity(Density density) {
        this.density = density;
    }
}

