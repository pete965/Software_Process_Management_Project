package com.soulroomie.demo.consumer.service.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("post_info")
@Data
@Builder
public class PostModel {
    @TableId(value = "id", type = IdType.AUTO)
    int id;
    String title;
    String userName;
    String postTime;
    String contents;
    String address;
}
