package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.service.mapper.UserMapper;
import com.soulroomie.demo.consumer.service.model.UserModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class UserService {

    private final static String ERROR_MESSAGE = "UserName or password error";

    @Resource
    UserMapper userMapper;
    public Result login(UserModel userModel){
        if (userExist(userModel.getUserName())){
            UserModel user = searchUser(userModel.getUserName());
            if (user.getPassword().equals(userModel.getPassword())) {
                String userName = user.getUserName();
                return Result.ok().data("userName", userName);
            } else {
                return Result.error().message(ERROR_MESSAGE);
            }
        } else {
            return Result.error().message(ERROR_MESSAGE);
        }
    }

    @Transactional
    public Result register(UserModel userModel) {
        if (!userExist(userModel.getUserName())) {
            String uuid = UUID.randomUUID().toString();
            userModel.setUuid(uuid);
            userMapper.insert(userModel);
        } else {
            return Result.error();
        }
        return Result.ok();
    }

    private boolean userExist(String userName) {
        return searchUser(userName) != null;
    }

    private UserModel searchUser(String userName) {
        LambdaQueryWrapper<UserModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserModel::getUserName, userName);
        return userMapper.selectOne(queryWrapper);
    }

    public Result isUserExist(String userName) {
        if (userExist(userName)) {
            return Result.ok().message("User already Exists").data("UserExist", true);
        } else {
            return Result.ok().data("UserExist", false);
        }
    }
}
