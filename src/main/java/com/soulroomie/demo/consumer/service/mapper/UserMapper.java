package com.soulroomie.demo.consumer.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soulroomie.demo.consumer.service.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserModel> {

}
