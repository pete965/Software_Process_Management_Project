package com.soulroomie.demo.consumer.controller.voucher;

import com.soulroomie.demo.consumer.dto.AppointmentDetailDto;
import com.soulroomie.demo.consumer.dto.voucher.LoginCreDto;
import com.soulroomie.demo.consumer.service.AppointmentService;
import com.soulroomie.demo.consumer.service.VoucherService;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/voucher/login")
public class LoginController {
    @Resource
    AppointmentService appointmentService;
    @Resource
    VoucherService voucherService;

    @RequestMapping("/detail/view")
    @ResponseBody
    //view appointment record
    public Result viewDetail(@RequestBody AppointmentDetailDto appointmentDetailDto) {
        return appointmentService.viewDetail(appointmentDetailDto);
    }

    @RequestMapping("/customer")
    @ResponseBody
    //view appointment record
    public Result customerLogin(@RequestBody LoginCreDto loginCreDto) {
        return voucherService.customerLogin(loginCreDto);
    }
}
