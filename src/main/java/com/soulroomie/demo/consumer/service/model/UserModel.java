package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("User")
@Data
@Builder
public class UserModel {
    private String userName;
    private String password;
    private String uuid;
}
