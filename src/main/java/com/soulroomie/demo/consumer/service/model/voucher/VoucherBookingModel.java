package com.soulroomie.demo.consumer.service.model.voucher;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("voucher_booking")
@Data
@Builder
public class VoucherBookingModel {
    private int id;
    private String customer_email;
    private String service;
    private String delivery_mode;
    private String date;
    private String msg;
    private String accept;
}
