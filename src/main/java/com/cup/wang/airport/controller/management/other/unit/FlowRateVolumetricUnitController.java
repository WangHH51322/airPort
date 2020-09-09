package com.cup.wang.airport.controller.management.other.unit;

import com.cup.wang.airport.model.FlowRateVolumetricUnit;
import com.cup.wang.airport.model.RespBean;
import com.cup.wang.airport.model.TemperatureUnit;
import com.cup.wang.airport.service.FlowRateVolumetricUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/24 15:43
 */
@RestController
@RequestMapping("/management/others/flowratevolumetric/unit")
public class FlowRateVolumetricUnitController {

    @Autowired
    FlowRateVolumetricUnitService flowRateVolumetricUnitService;

    @GetMapping("/")
    public List<FlowRateVolumetricUnit> getAllFlowRateVolumetricUnit(){
        return flowRateVolumetricUnitService.getAllFlowRateVolumetricUnit();
    }

    @GetMapping("/{id}")
    public Optional<FlowRateVolumetricUnit> getFlowRateVolumetricUnitById(@PathVariable Integer id){
        return flowRateVolumetricUnitService.getFlowRateVolumetricUnitById(id);
    }

    @PutMapping("/")
    public RespBean updateFlowRateVolumetricUnit(@RequestBody FlowRateVolumetricUnit flowRateVolumetricUnit){
        if (flowRateVolumetricUnitService.updateFlowRateVolumetricUnit(flowRateVolumetricUnit) != null){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/")
    public RespBean addFlowRateVolumetricUnit(@RequestBody FlowRateVolumetricUnit flowRateVolumetricUnit){
        if (flowRateVolumetricUnitService.addFlowRateVolumetricUnit(flowRateVolumetricUnit) != null){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteFlowRateVolumetricUnit(@PathVariable Integer id){

        try {
            flowRateVolumetricUnitService.deleteFlowRateVolumetricUnitById(id);
            return RespBean.ok("删除成功!");
        } catch (Exception e) {
            return RespBean.error("删除失败!");
        }
    }

}
