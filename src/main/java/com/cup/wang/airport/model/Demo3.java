package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/30 13:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo3 implements Serializable {

    private Integer id;
    private String name;
    private Integer style;
}
