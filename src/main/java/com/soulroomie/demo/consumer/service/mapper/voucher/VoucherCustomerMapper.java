package com.soulroomie.demo.consumer.service.mapper.voucher;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soulroomie.demo.consumer.service.model.voucher.CustomerModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VoucherCustomerMapper  extends BaseMapper<CustomerModel> {
}
