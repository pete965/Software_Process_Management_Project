package com.soulroomie.demo.consumer.controller;

import com.soulroomie.demo.consumer.service.PostDirService;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user-postdirectory")
public class PostDirController {

    @Resource
    PostDirService postDirService;

    @RequestMapping("/getall")
    @ResponseBody
    /**
     * get all post from database
     */
    public Result getAllPost() {
        return postDirService.getAll();
    }

}
