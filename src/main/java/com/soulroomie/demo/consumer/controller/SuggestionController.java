package com.soulroomie.demo.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soulroomie.demo.consumer.service.SuggestionService;
import com.soulroomie.demo.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("suggestion")
public class SuggestionController {


    final
    SuggestionService suggestionService;

    @Autowired
    public SuggestionController (SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/list")
    @ResponseBody
    private Result listSuggestion(@RequestBody String userNameString) {
        JSONObject jsonObject = JSON.parseObject(userNameString);
        return Result.ok().data("UserLists", suggestionService.getSuggestionList(jsonObject.getString("userName")));
    }

}
