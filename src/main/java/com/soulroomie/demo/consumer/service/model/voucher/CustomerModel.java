package com.soulroomie.demo.consumer.service.model.voucher;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("Voucher_customer")
@Data
@Builder
public class CustomerModel {
    private String name;
    private String phone;
    private String email;
    private String password;
}
