package com.cup.wang.airport.service;

import com.cup.wang.airport.mapper.SettingsDataTypeValueChildrenMapper;
import com.cup.wang.airport.model.SettingsDataTypeValueChildren;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/8/30 22:33
 */
@Service
public class SettingsDataTypeValueChildrenService {

    @Autowired
    public SettingsDataTypeValueChildrenMapper sDTVChildrenMapper;

    public SettingsDataTypeValueChildren getById(Integer id) {
        return sDTVChildrenMapper.selectByPrimaryKey(id);
    }
}
