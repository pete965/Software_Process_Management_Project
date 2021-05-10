package com.soulroomie.demo.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soulroomie.demo.consumer.dto.UserDto;
import com.soulroomie.demo.consumer.service.UserService;
import com.soulroomie.demo.consumer.service.model.UserModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class ConsumerController {
    @Resource
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody UserDto userDto) {
        UserModel userModel = createUserModel(userDto);
        return userService.login(userModel);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody UserDto userDto) {
        UserModel userModel = createUserModel(userDto);
        return userService.register(userModel);
    }

    private UserModel createUserModel(UserDto userDto) {
        return UserModel.builder().userName(userDto.getUserName()).password(userDto.getPassword()).build();
    }

    /**
     * if exist user return false
     *
     * @param jsonString
     * @return
     */
    @PostMapping("/userExists")
    @ResponseBody
    public Result isUserExist( @RequestBody String jsonString) {

        //jsonString = "{"userName": "LNX" "}"
        JSONObject jsonObject = JSON.parseObject(jsonString);
        return userService.isUserExist(jsonObject.getString("userName"));
    }
}
