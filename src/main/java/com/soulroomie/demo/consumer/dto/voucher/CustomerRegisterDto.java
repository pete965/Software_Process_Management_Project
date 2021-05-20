package com.soulroomie.demo.consumer.dto.voucher;

import lombok.Data;

@Data
public class CustomerRegisterDto {
    private String name;
    private String password;
    private String phone;
    private String email;
}
