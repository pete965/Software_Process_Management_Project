package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("post_image")
@Data
@Builder
public class ImageModel {
//    Integer id;
    int postId;
    String imageUrl;
}
