package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("location")
public class LocationModel {
    private String username;
    private double longitude;
    private double latitude;
}
