package com.soulroomie.demo.consumer.controller.voucher;

import com.soulroomie.demo.consumer.dto.voucher.*;
import com.soulroomie.demo.consumer.service.VoucherService;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/voucher/customer")
public class CustomerController {
    @Resource
    VoucherService voucherService;
    @RequestMapping("/register")
    @ResponseBody
    //view appointment record
    public Result customerRegister(@RequestBody CustomerRegisterDto customerRegisterDto) {
        return voucherService.customerRegister(customerRegisterDto);
    }
    @RequestMapping("/information/update")
    @ResponseBody
    //view appointment record
    public Result updateInformation(@RequestBody CustomerRegisterDto customerRegisterDto) {
        return voucherService.updateInformation(customerRegisterDto);
    }
    @RequestMapping("/biller/add")
    @ResponseBody
    //view appointment record
    public Result addBiller(@RequestBody BillerInformationDto billerInformationDto) {
        return voucherService.addBiller(billerInformationDto);
    }
    @RequestMapping("/biller/update")
    @ResponseBody
    //view appointment record
    public Result updateBiller(@RequestBody BillerInformationDto billerInformationDto) {
        return voucherService.updateBiller(billerInformationDto);
    }
    @RequestMapping("/voucher/book")
    @ResponseBody
    //view appointment record
    public Result bookVoucher(@RequestBody VoucherBookingDto voucherBookingDto) {
        return voucherService.bookVoucher(voucherBookingDto);
    }
    @RequestMapping("/voucher/cancel")
    @ResponseBody
    //view appointment record
    public Result cancelVoucher(@RequestBody VoucherCancelDto voucherCancelDto) {
        return voucherService.cancelVoucher(voucherCancelDto);
    }
    @RequestMapping("/voucher/view")
    @ResponseBody
    //view appointment record
    public Result viewVoucher(@RequestBody VoucherViewDto voucherViewDto) {
        return voucherService.viewVoucher(voucherViewDto);
    }
}
