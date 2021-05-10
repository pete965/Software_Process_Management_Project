package com.soulroomie.demo.consumer.controller;

import com.soulroomie.demo.consumer.dto.AppointmentDetailDto;
import com.soulroomie.demo.consumer.dto.AppointmentOperationDto;
import com.soulroomie.demo.consumer.service.AppointmentService;
import com.soulroomie.demo.consumer.service.model.AppointmentModel;
import com.soulroomie.demo.consumer.service.model.AppointmentRequestModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Resource
    AppointmentService appointmentService;

    @RequestMapping("/detail/view")
    @ResponseBody
    //view appointment record
    public Result viewDetail(@RequestBody AppointmentDetailDto appointmentDetailDto) {
        return appointmentService.viewDetail(appointmentDetailDto);
    }

    @RequestMapping("/detail/save")
    @ResponseBody
    //save or edit appointment record
    public Result saveDetail(@RequestBody AppointmentModel appointmentModel) {
        return appointmentService.saveDetail(appointmentModel);
    }

    @RequestMapping("/list/view")
    @ResponseBody
    //view application list of a specific user
    public Result viewList(@RequestBody AppointmentOperationDto appointmentOperationDto) {
        return appointmentService.viewList(appointmentOperationDto);
    }

    @RequestMapping("/list/operate")
    @ResponseBody
    //handle application
    public Result operateList(@RequestBody AppointmentOperationDto appointmentOperationDto) {
        return appointmentService.operateList(appointmentOperationDto);
    }

    @RequestMapping("/list/add")
    @ResponseBody
    //add a new application
    public Result addApplication(@RequestBody AppointmentRequestModel appointmentRequestModel){
        return appointmentService.addApplication(appointmentRequestModel);
    }

    @RequestMapping("/view/apply")
    @ResponseBody
    //add a new application
    public Result viewApplyStatus(@RequestBody AppointmentRequestModel appointmentRequestModel){
        return appointmentService.viewApplyStatus(appointmentRequestModel);
    }
}
