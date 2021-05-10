package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("post_info")
public class PostDirModel {
    private String id;
    private String userName;
    private String title;
    private String postTime;
    private String contents;
}
