package com.soulroomie.demo.consumer.dto;

import lombok.Data;

@Data
public class AppointmentOperationDto {
    private String operation;
    private String sender;
    private String receiver;
}
