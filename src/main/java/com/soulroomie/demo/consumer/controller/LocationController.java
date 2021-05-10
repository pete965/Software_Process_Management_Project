package com.soulroomie.demo.consumer.controller;

import com.soulroomie.demo.consumer.service.LocationService;
import com.soulroomie.demo.consumer.service.model.LocationModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Resource
    LocationService locationService;
    @RequestMapping("/view")
    @ResponseBody
    public Result viewLocation(@RequestBody LocationModel locationModel) {
        return locationService.searchLocation(locationModel);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result updateLocation(@RequestBody LocationModel locationModel) {
        return locationService.updateLocation(locationModel);
    }
}
