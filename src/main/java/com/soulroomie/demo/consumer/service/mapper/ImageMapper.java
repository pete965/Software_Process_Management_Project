package com.soulroomie.demo.consumer.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soulroomie.demo.consumer.service.model.ImageModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper extends BaseMapper<ImageModel> {
}
