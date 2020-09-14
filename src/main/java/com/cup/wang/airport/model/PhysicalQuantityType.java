package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalQuantityType implements Serializable {
    private Integer id;

    private String tPhysicalQuantity;

    private String tName;

    private Integer tType;
}