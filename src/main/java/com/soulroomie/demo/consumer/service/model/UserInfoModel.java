package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfoModel {
//    private String uuid;
    private int id;
    private String username;
    private String nickname;
    private String age;
    private String sex;
    private String job;
    private String stage;
    private String pet;
    private String emailAddress;
    private String profile;
    private String profilePicture;
}
