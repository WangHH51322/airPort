package com.cup.wang.airport.model.vapor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaporFitting implements Serializable {
    private Integer id;

    private Integer vaporId;

    private Double vapor;

    private Double temperature;
}