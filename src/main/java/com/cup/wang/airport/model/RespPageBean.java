package com.cup.wang.airport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/7 14:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespPageBean {
    private Long total;
    private List<?> data;
}
