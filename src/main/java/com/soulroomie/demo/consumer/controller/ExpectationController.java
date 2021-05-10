package com.soulroomie.demo.consumer.controller;

import com.soulroomie.demo.consumer.service.ExpectationService;
import com.soulroomie.demo.consumer.service.model.ExpectationModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/expectation")

public class ExpectationController {
    @Resource
    ExpectationService expectationService;

    @RequestMapping("/add")
    @ResponseBody
    /**
     * add expectation information
     */
    public Result addExpectationInformation(@RequestBody ExpectationModel expectation) {
        return expectationService.addExpectationInformation(expectation);
    }

    @RequestMapping("/edit")
    @ResponseBody
    /**
     * edit expectation information
     */
    public Result editExpectationInformation(@RequestBody ExpectationModel expectation) {
        return expectationService.editExpectationInformation(expectation);
    }

    @RequestMapping("/view")
    @ResponseBody
    /**
     * view expectation information
     */
    public Result viewExpectationInformation(@RequestBody ExpectationModel expectation) {
        return expectationService.viewExpectationInformation(expectation);
    }

    @RequestMapping("/delete")
    @ResponseBody
    /**
     * delete expectation information
     */
    public Result deleteExpectationInformation(@RequestBody ExpectationModel expectation) {
        return expectationService.deleteExpectationInformation(expectation);
    }


}
