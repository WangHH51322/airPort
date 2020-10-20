package com.cup.wang.airport.model.unit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalQuantity {
    private Integer id;
    private String unitNameIso;
    private String symbol;
    private String quantityName;
    private String englishName;
    private String unitSymbolIso;
    private List<QuantityUnit> quantityUnits;
}