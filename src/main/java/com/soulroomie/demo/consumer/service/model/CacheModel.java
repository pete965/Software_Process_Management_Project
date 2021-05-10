package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cache")
public class CacheModel {
    private int id;
    private String f1;
    private String f2;
    private String f3;
}
