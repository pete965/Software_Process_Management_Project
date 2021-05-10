package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("appointment_request")
public class AppointmentRequestModel {
    private String receiver;
    private String sender;
    private String status;
}
