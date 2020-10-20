package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valve implements Serializable {
    private Integer id;

    private String name;

    private Integer numb;

    private Integer projectId;

    private Integer startId;

    private String startName;

    private Integer endId;

    private String endName;

    private Double cv;

}