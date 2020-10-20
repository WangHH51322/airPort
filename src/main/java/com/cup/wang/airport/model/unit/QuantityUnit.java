package com.cup.wang.airport.model.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityUnit {
    private Integer id;

    private String unitName;

    private String symbol;

    private String chineseName;

    private String englishName;

    private Double factorA;

    private Double factorB;

    private Integer unitSystemId;

    private Integer physicalQuantityId;

}