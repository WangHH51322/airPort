package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.SettingsDataMapper;
import com.cup.wang.airport.model.settingsData.SettingsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/30 22:51
 */
@Service
public class SettingsDataService {

    @Autowired
    SettingsDataMapper settingsDataMapper;

    public List<SettingsData> getAll() {
        return settingsDataMapper.getAll();
    }
}
