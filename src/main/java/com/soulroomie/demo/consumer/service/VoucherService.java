package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.dto.voucher.*;
import com.soulroomie.demo.consumer.service.mapper.voucher.BillerMapper;
import com.soulroomie.demo.consumer.service.mapper.voucher.ServiceTypeMapper;
import com.soulroomie.demo.consumer.service.mapper.voucher.VoucherBookingMapper;
import com.soulroomie.demo.consumer.service.mapper.voucher.VoucherCustomerMapper;
import com.soulroomie.demo.consumer.service.model.voucher.BillerInformationModel;
import com.soulroomie.demo.consumer.service.model.voucher.CustomerModel;
import com.soulroomie.demo.consumer.service.model.voucher.ServiceTypeModel;
import com.soulroomie.demo.consumer.service.model.voucher.VoucherBookingModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VoucherService {
    @Resource
    VoucherCustomerMapper voucherCustomerMapper;
    @Resource
    BillerMapper billerMapper;
    @Resource
    VoucherBookingMapper voucherBookingMapper;
    @Resource
    ServiceTypeMapper serviceTypeMapper;

    public Result customerLogin(LoginCreDto loginCreDto) {
        LambdaQueryWrapper<CustomerModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerModel::getEmail, loginCreDto.getEmail());
        queryWrapper.eq(CustomerModel::getPassword, loginCreDto.getPassword());
        CustomerModel customerModel = voucherCustomerMapper.selectOne(queryWrapper);
        if (customerModel != null){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    public Result adminLogin(LoginCreDto loginCreDto) {
        if (loginCreDto.getEmail().equals("admin")&&loginCreDto.getPassword().equals("admin")){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    public Result customerRegister(CustomerRegisterDto customerRegisterDto) {
        CustomerModel customerModel = CustomerModel.builder().email(customerRegisterDto.getEmail()).
                name(customerRegisterDto.getName()).password(customerRegisterDto.getPassword()).
                phone(customerRegisterDto.getPhone()).build();
        System.out.println(customerModel.toString());
        voucherCustomerMapper.insert(customerModel);
        return Result.ok();
    }

    public Result updateInformation(CustomerRegisterDto customerRegisterDto) {
        LambdaQueryWrapper<CustomerModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerModel::getEmail,customerRegisterDto.getEmail());
        CustomerModel customerModel = CustomerModel.builder().email(customerRegisterDto.getEmail()).
                name(customerRegisterDto.getName()).password(customerRegisterDto.getPassword()).
                phone(customerRegisterDto.getPhone()).build();
        voucherCustomerMapper.update(customerModel,queryWrapper);
        return Result.ok();
    }

    public Result addBiller(BillerInformationDto billerInformationDto) {
        BillerInformationModel billerInformationModel = BillerInformationModel.builder()
                .biller_email(billerInformationDto.getBillerEmail())
                .customer_email(billerInformationDto.getCustomerEmail())
                .name(billerInformationDto.getName()).build();
        billerMapper.insert(billerInformationModel);
        return Result.ok();
    }

    public Result updateBiller(BillerInformationDto billerInformationDto) {
        BillerInformationModel billerInformationModel = BillerInformationModel.builder()
                .biller_email(billerInformationDto.getBillerEmail())
                .customer_email(billerInformationDto.getCustomerEmail())
                .name(billerInformationDto.getName()).build();
        LambdaQueryWrapper<BillerInformationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BillerInformationModel::getBiller_email,billerInformationDto.getBillerEmail());
        queryWrapper.eq(BillerInformationModel::getCustomer_email,billerInformationDto.getCustomerEmail());
        billerMapper.update(billerInformationModel,queryWrapper);
        return Result.ok();
    }

    public Result bookVoucher(VoucherBookingDto voucherBookingDto) {
        VoucherBookingModel voucherBookingModel = VoucherBookingModel.builder()
                .customer_email(voucherBookingDto.getCustomerEmail())
                .date(voucherBookingDto.getDate())
                .delivery_mode(voucherBookingDto.getDeliveryMode())
                .msg(voucherBookingDto.getMsg())
                .accept("not")
                .service(voucherBookingDto.getService()).build();
        voucherBookingMapper.insert(voucherBookingModel);
        return Result.ok();
    }

    public Result cancelVoucher(VoucherCancelDto voucherCancelDto) {
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VoucherBookingModel::getId,voucherCancelDto.getVoucherBookingId());
        voucherBookingMapper.delete(queryWrapper);
        return Result.ok();
    }

    public Result viewVoucher(VoucherViewDto voucherViewDto) {
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VoucherBookingModel::getCustomer_email,voucherViewDto.getCustomerEmailAddress());
        return Result.ok().data("voucherList",voucherBookingMapper.selectList(queryWrapper));
    }

    public Result acceptVoucherBooking(VoucherAcceptDto voucherAcceptDto) {
        VoucherBookingModel voucherBookingModel = VoucherBookingModel.builder().accept("accept").build();
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VoucherBookingModel::getId,voucherAcceptDto.getBookingId());
        voucherBookingMapper.update(voucherBookingModel,queryWrapper);
        return Result.ok();
    }

    public Result viewVoucherBooking() {
        LambdaQueryWrapper<VoucherBookingModel> queryWrapper = new LambdaQueryWrapper<>();
        return Result.ok().data("voucherList",voucherBookingMapper.selectList(queryWrapper));
    }

    public Result addServiceType(AddServiceDto addServiceDto) {
        ServiceTypeModel serviceTypeModel = ServiceTypeModel.builder().name(addServiceDto.getName()).build();
        serviceTypeMapper.insert(serviceTypeModel);
        return Result.ok();
    }

    public Result viewServiceType() {
        LambdaQueryWrapper<ServiceTypeModel> queryWrapper = new LambdaQueryWrapper<>();
        return Result.ok().data("voucherList",serviceTypeMapper.selectList(queryWrapper));
    }

    public Result viewInformation(VoucherViewDto customerRegisterDto) {
        String email = customerRegisterDto.getCustomerEmailAddress();
        LambdaQueryWrapper<CustomerModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerModel::getEmail,email);
        return Result.ok().data("customer",voucherCustomerMapper.selectOne(queryWrapper));
    }
}
