package com.cup.wang.airport.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pipe implements Serializable {
    private Integer id;

    private String name;

    private Integer numb;

    private Integer projectId;

    private Integer startId;

    private String startName;

    private Integer endId;

    private String endName;

    private Double length;

    private Double outsideDiameter;

    private Double roughness;

    private Double thickness;

    private Double k;

    private Double er;

}