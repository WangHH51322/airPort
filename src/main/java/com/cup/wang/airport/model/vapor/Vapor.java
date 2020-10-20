package com.cup.wang.airport.model.vapor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vapor implements Serializable {
    private Integer id;
    private Integer vaporUnitId;
    private Integer vaporValueId;
    private Integer liquidId;

    private VaporConstant vaporConstant;
    private List<VaporFitting> vaporFittings;
}