package com.cup.wang.airport.controller.management.table.settings;

import com.cup.wang.airport.model.settingsData.SettingsDataTypeValueChildren;
import com.cup.wang.airport.service.SettingsDataTypeValueChildrenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/30 22:33
 */
@RestController
@RequestMapping("/management/table/settings/children")
public class SettingsDataTypeValueChildrenController {

    @Autowired
    SettingsDataTypeValueChildrenService sDTVChildrenService;

    @GetMapping("/{id}")
    public SettingsDataTypeValueChildren getById(@PathVariable Integer id){
        return sDTVChildrenService.getById(id);
    }
}
