package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("appointment")
public class AppointmentModel {
    private String address;
    private String owner;
    private String applicant;
}
