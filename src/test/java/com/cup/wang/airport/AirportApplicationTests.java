package com.cup.wang.airport;

import com.cup.wang.airport.mapper.*;
import com.cup.wang.airport.model.DensityFitting;
import com.cup.wang.airport.service.DensityFittingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AirportApplicationTests {

    @Autowired
    DensityMapper densityTestMapper;

    @Autowired
    private DensityFittingService densityFittingService;

    @Test
    void contextLoads() {
        //System.out.println(demoMapper.getAllDemos(2));
        System.out.println(densityTestMapper.selectByKey( 1,2));
    }

    @Test
    void testUpdate() {
        DensityFitting densityFitting = new DensityFitting();
        Integer result = densityFittingService.addDensityFitting(densityFitting);
        System.out.println(result);
    }

}
