package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.FlowRatePumpCurveUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.TemperatureUnit;
import com.cup.wang.airport.service.FlowRatePumpCurveUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 15:21
 */
@RestController
@RequestMapping("/management/others/flowratepumpcurve/unit")
public class FlowRatePumpCurveUnitController {

    @Autowired
    FlowRatePumpCurveUnitService flowRatePumpCurveUnitService;

    @GetMapping("/")
    public List<FlowRatePumpCurveUnit> getAllFlowRatePumpCurveUnit(){
        return flowRatePumpCurveUnitService.getAllFlowRatePumpCurveUnit();
    }

    @GetMapping("/{id}")
    public Optional<FlowRatePumpCurveUnit> getFlowRatePumpCurveUnitById(@PathVariable Integer id){
        return flowRatePumpCurveUnitService.getFlowRatePumpCurveUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateFlowRatePumpCurveUnit(@RequestBody FlowRatePumpCurveUnit flowRatePumpCurveUnit){
        if (flowRatePumpCurveUnitService.updateFlowRatePumpCurveUnit(flowRatePumpCurveUnit) != null){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/")
    public RespBean addFlowRatePumpCurveUnit(@RequestBody FlowRatePumpCurveUnit flowRatePumpCurveUnit){
        if (flowRatePumpCurveUnitService.addFlowRatePumpCurveUnit(flowRatePumpCurveUnit) != null){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteFlowRatePumpCurveUnitById(@PathVariable Integer id){

        try {
            flowRatePumpCurveUnitService.deleteFlowRatePumpCurveUnitById(id);
            return RespBean.ok("删除成功!");
        } catch (Exception e) {
            return RespBean.error("删除失败!");
        }
    }
}
