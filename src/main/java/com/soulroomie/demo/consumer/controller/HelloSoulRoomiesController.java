package com.soulroomie.demo.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consumer")
public class HelloSoulRoomiesController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello soulroomies";
    }
}
