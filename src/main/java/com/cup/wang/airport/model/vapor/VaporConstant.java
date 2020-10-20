package com.cup.wang.airport.model.vapor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaporConstant implements Serializable {
    private Integer id;

    private Double value;

    private Integer vaporId;
}