package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/30 13:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo implements Serializable {

    private Integer id;
    private String name;
    private Integer style;

    private Object demo;
//    private Demo1 demo1;
//    private Demo2 demo2;
//    private Demo3 demo3;
}
