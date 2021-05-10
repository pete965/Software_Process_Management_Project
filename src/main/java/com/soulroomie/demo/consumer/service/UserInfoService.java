package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.service.mapper.UserInfoMapper;
import com.soulroomie.demo.consumer.service.model.UserInfoModel;
import com.soulroomie.demo.tools.ImageHelper;
import com.soulroomie.demo.tools.Result;
import com.soulroomie.demo.tools.http.HttpHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    public static final int NUM = 0;
    public static String message = "";

    @Transactional
    public Result addUserInformation(UserInfoModel user) throws IOException {
        UserInfoModel existUserInfo = queryUserInfo(user);
        if (existUserInfo != null) {
            message = "User already Exists";
            return Result.ok().message(message);
        }

        // transfer byteString to byte[] and upload picture to oss
        String pictureUrl = "";
        if (user.getProfilePicture() != null && user.getProfilePicture().length() < 200){
            pictureUrl = user.getProfilePicture();
        }

        if (user.getProfilePicture() != null && user.getProfilePicture().length() >= 200){
            byte[]  pictureByte = ImageHelper.string2Image(user.getProfilePicture());
            HttpHelper httpHelper = new HttpHelper();
            pictureUrl = httpHelper.upload(pictureByte);
        }
        user.setProfilePicture(pictureUrl);

        if (userInfoMapper.insert(user) > NUM)
            return Result.ok();
        else return Result.error();
    }

    @Transactional
    public Result editUserInformation(UserInfoModel user) {
        try {
            LambdaQueryWrapper<UserInfoModel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserInfoModel::getUsername, user.getUsername());
            UserInfoModel existUserInfo = userInfoMapper.selectOne(queryWrapper);

            // transfer byteString to byte[] and upload picture to oss
            String pictureUrl = "";
            if (user.getProfilePicture() != null && user.getProfilePicture().length() < 200){
                pictureUrl = user.getProfilePicture();
            }

            if (user.getProfilePicture() != null && user.getProfilePicture().length() >= 200){
                byte[]  pictureByte = ImageHelper.string2Image(user.getProfilePicture());
                HttpHelper httpHelper = new HttpHelper();
                pictureUrl = httpHelper.upload(pictureByte);
            }
            user.setProfilePicture(pictureUrl);

            if (existUserInfo == null) {
                message = "User not exist!";
                return Result.ok().message(message);
            }
            if (userInfoMapper.update(user, queryWrapper) > NUM)
                return Result.ok();
            else return Result.error();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return Result.error();
        }
    }

    @Transactional
    public Result viewUserInformation(UserInfoModel user) {
        UserInfoModel existUserInfo = queryUserInfo(user);
        System.out.println(user);
        if (existUserInfo == null) {
            message = "User not exist!";
            return Result.ok().message(message);
        }
//        return JSON.toJSONString(existUserInfo);
//        System.out.println(existUserInfo);
        return Result.ok().data("UserInfo", existUserInfo);
    }

    @Transactional
    public Result deleteUserInformation(UserInfoModel user) {
        LambdaQueryWrapper<UserInfoModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfoModel::getUsername, user.getUsername());
        UserInfoModel existUserInfo = userInfoMapper.selectOne(queryWrapper);
        if (existUserInfo == null) {
            message = "User not exist!";
            return Result.ok().message(message);
        }
        if (userInfoMapper.delete(queryWrapper) > NUM)
            return Result.ok();
        else return Result.error();
    }

    public UserInfoModel queryUserInfo(UserInfoModel user) {
        LambdaQueryWrapper<UserInfoModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfoModel::getUsername, user.getUsername());
        UserInfoModel existUserInfo = userInfoMapper.selectOne(queryWrapper);
        return existUserInfo;
    }

    /**
     * select by userNames
     * @param userNames
     * @return
     */
    public List<UserInfoModel> queryByUserNames(List<String> userNames) {
        if (userNames.size() == 0) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<UserInfoModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(UserInfoModel::getUsername, userNames);
        return userInfoMapper.selectList(queryWrapper);
    }


}
