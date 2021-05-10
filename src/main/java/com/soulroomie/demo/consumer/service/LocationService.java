package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.service.mapper.LocationMapper;
import com.soulroomie.demo.consumer.service.model.LocationModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LocationService {

    private String noLocationMessage ="No Location Now";
    @Resource
    LocationMapper locationMapper;
    public Result searchLocation(LocationModel locationModel) {
        LambdaQueryWrapper<LocationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LocationModel::getUsername,locationModel.getUsername());
        LocationModel existLocationModel = locationMapper.selectOne(queryWrapper);
        if (existLocationModel == null){
            return Result.ok().message(noLocationMessage);
        }else {
            return Result.ok().data("location",existLocationModel);
        }
    }

    public Result updateLocation(LocationModel locationModel) {
        LambdaQueryWrapper<LocationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LocationModel::getUsername,locationModel.getUsername());
        LocationModel searchedLocationModel = locationMapper.selectOne(queryWrapper);
        if (searchedLocationModel == null){
            locationMapper.insert(locationModel);
            return Result.ok();
        }else {
            locationMapper.update(locationModel,queryWrapper);
            return Result.ok();
        }
    }
}
