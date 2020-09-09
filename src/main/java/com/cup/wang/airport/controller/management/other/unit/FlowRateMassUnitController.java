package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.FlowRateMassUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.TemperatureUnit;
import com.cup.wang.airport.service.FlowRateMassUnitService;
import com.cup.wang.airport.service.TemperatureUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 9:54
 */
@RestController
@RequestMapping("/management/others/flowratemass/unit")
public class FlowRateMassUnitController {

    @Autowired
    FlowRateMassUnitService flowRateMassUnitService;


    @GetMapping("/")
    public List<FlowRateMassUnit> getAllFlowRateMassUnit(){
        return flowRateMassUnitService.getAllFlowRateMassUnit();
    }

    @GetMapping("/{id}")
    public Optional<FlowRateMassUnit> getFlowRateMassUnitById(@PathVariable Integer id){
        return flowRateMassUnitService.getFlowRateMassUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateFlowRateMassUnit(@RequestBody FlowRateMassUnit flowRateMassUnit){
        if (flowRateMassUnitService.updateFlowRateMassUnit(flowRateMassUnit) != null){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/")
    public RespBean addFlowRateMassUnit(@RequestBody FlowRateMassUnit flowRateMassUnit){
        if (flowRateMassUnitService.addFlowRateMassUnit(flowRateMassUnit) != null){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteFlowRateMassUnitById(@PathVariable Integer id){

        try {
            flowRateMassUnitService.deleteFlowRateMassUnitById(id);
            return RespBean.ok("删除成功!");
        } catch (Exception e) {
            return RespBean.error("删除失败!");
        }
    }

}
