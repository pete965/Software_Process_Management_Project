package com.soulroomie.demo.consumer.dto.voucher;

import lombok.Data;

@Data
public class VoucherBookingDto {
    private String customerEmail;
    private String service;
    private String deliveryMode;
    private String date;
    private String msg;
}
