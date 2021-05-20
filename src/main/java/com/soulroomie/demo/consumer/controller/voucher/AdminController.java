package com.soulroomie.demo.consumer.controller.voucher;

import com.soulroomie.demo.consumer.dto.voucher.LoginCreDto;
import com.soulroomie.demo.consumer.service.VoucherService;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/voucher/login")
public class AdminController {
    @Resource
    VoucherService voucherService;
//    @RequestMapping("/customer")
//    @ResponseBody
//    //view appointment record
//    public Result customerLogin(@RequestBody LoginCreDto loginCreDto) {
//        return voucherService.customerLogin(loginCreDto);
//    }
}
