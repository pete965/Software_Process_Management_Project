package com.soulroomie.demo.consumer.controller;

import com.soulroomie.demo.consumer.service.UserInfoService;
import com.soulroomie.demo.consumer.service.model.UserInfoModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user-information")
/**
 * User Information Control Sample Code
 */
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @RequestMapping("/add")
    @ResponseBody
    /**
     * add user information
     */
    public Result addUserInformation(@RequestBody UserInfoModel user) throws Exception {
        return userInfoService.addUserInformation(user);
    }

    @RequestMapping("/edit")
    @ResponseBody
    /**
     * edit user information
     */
    public Result editUserInformation(@RequestBody UserInfoModel user) {
        return userInfoService.editUserInformation(user);
    }

    @RequestMapping("/view")
    @ResponseBody
    /**
     * view user information
     */
    public Result viewUserInformation(@RequestBody UserInfoModel user) {
        return userInfoService.viewUserInformation(user);
    }

    @RequestMapping("/delete")
    @ResponseBody
    /**
     * delete user information
     */
    public Result deleteUserInformation(@RequestBody UserInfoModel user) {
        return userInfoService.deleteUserInformation(user);
    }

}
