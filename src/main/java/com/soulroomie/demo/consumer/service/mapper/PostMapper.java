package com.soulroomie.demo.consumer.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soulroomie.demo.consumer.service.model.PostModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends BaseMapper<PostModel> {
}
