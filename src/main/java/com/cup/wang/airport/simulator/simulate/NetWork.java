package com.cup.wang.airport.simulator.simulate;

import com.cup.wang.airport.model.Node;
import com.cup.wang.airport.model.Pipe;
import com.cup.wang.airport.model.Valve;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/30 21:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetWork implements Serializable {

    private List<Node> nodes;
    private List<Pipe> pipes;
    private List<Valve> valves;

}
