package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node implements Serializable {

    private Integer id;
    private String name;
    private Integer projectId;
    private Integer numb;
    private Double flow;
    private Double pressure;
    private Double temperature;
    private Double lrc;
    private Integer bcType;
    private Integer nodeType;

}