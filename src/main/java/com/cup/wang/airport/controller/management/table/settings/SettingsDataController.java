package com.cup.wang.airport.controller.management.table.settings;

import com.cup.wang.airport.model.SettingsData;
import com.cup.wang.airport.service.SettingsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/30 22:51
 */
@RestController
@RequestMapping("/management/table/settings/data")
public class SettingsDataController {

    @Autowired
    SettingsDataService settingsDataService;

    @GetMapping("/")
    public List<SettingsData> getAll(){
        return settingsDataService.getAll();
    }
}
