package com.soulroomie.demo.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.soulroomie.demo.consumer.service.CacheService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Mybatis DB connection sample interface
 */
@Controller
@RequestMapping("/cache")
public class CacheController {
    @Resource
    CacheService cacheService;

    @RequestMapping("/list")
    @ResponseBody
    public String addBasicInformation() {
        return JSON.toJSONString(cacheService.listAllCaches());
    }
}
