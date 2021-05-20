package com.soulroomie.demo.consumer.controller.voucher;

import com.soulroomie.demo.consumer.dto.voucher.AddServiceDto;
import com.soulroomie.demo.consumer.dto.voucher.VoucherAcceptDto;
import com.soulroomie.demo.consumer.service.VoucherService;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/voucher/admin")
public class AdminController {
    @Resource
    VoucherService voucherService;

    @RequestMapping("/voucher/accept")
    @ResponseBody
    //view appointment record
    public Result acceptVoucherBooking(@RequestBody VoucherAcceptDto voucherAcceptDto) {
        return voucherService.acceptVoucherBooking(voucherAcceptDto);
    }
    @RequestMapping("/voucher/view")
    @ResponseBody
    //view appointment record
    public Result viewVoucherBooking() {
        return voucherService.viewVoucherBooking();
    }
    @RequestMapping("/service/add")
    @ResponseBody
    //view appointment record
    public Result addServiceType(@RequestBody AddServiceDto addServiceDto) {
        return voucherService.addServiceType(addServiceDto);
    }
    @RequestMapping("/service/view")
    @ResponseBody
    //view appointment record
    public Result viewServiceType() {
        return voucherService.viewServiceType();
    }
}
