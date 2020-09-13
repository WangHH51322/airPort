package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitSystem {
    private Integer id;

    private String systemName;

    private String description;

    List<QuantityUnit> quantityUnits;
}