package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.dto.voucher.LoginCreDto;
import com.soulroomie.demo.consumer.service.mapper.voucher.VoucherCustomerMapper;
import com.soulroomie.demo.consumer.service.model.voucher.CustomerModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VoucherService {
    @Resource
    VoucherCustomerMapper voucherCustomerMapper;
    public Result customerLogin(LoginCreDto loginCreDto) {
        if (true){
            return Result.ok();
        }
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
}
