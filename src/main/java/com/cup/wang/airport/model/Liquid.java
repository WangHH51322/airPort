package com.cup.wang.airport.model;

import com.cup.wang.airport.model.density.Density;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/19 22:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Liquid implements Serializable {
    private Integer id;
    private String name;
    private Integer densityId;
    private Density density;
}

