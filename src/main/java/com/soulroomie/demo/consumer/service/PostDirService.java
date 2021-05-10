package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.dto.PostDirDto;
import com.soulroomie.demo.consumer.service.mapper.PostDirMapper;
import com.soulroomie.demo.consumer.service.mapper.UserInfoMapper;
import com.soulroomie.demo.consumer.service.model.PostDirModel;
import com.soulroomie.demo.consumer.service.model.UserInfoModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostDirService {
    public static String message = "";
    @Resource
    private PostDirMapper postDirMapper;
    @Resource
    private UserInfoMapper userInfoMapper;


    @Transactional
    public Result getAll() {
        List<PostDirDto> postDirList = new ArrayList<>();
        List<PostDirModel> list2 = postDirMapper.selectList(null);
        if (list2 == null) {
            message = "Posts not exist!";
            return Result.ok().message(message);
        }
        for (PostDirModel postDirModel : list2) {
            String url = "";
            LambdaQueryWrapper<UserInfoModel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserInfoModel::getUsername, postDirModel.getUserName());
            UserInfoModel existUserInfo = userInfoMapper.selectOne(queryWrapper);
            if (existUserInfo != null) {
                url = existUserInfo.getProfilePicture();
            }
            PostDirDto postDirDto = new PostDirDto(postDirModel.getId(), postDirModel.getUserName(), postDirModel.getTitle(), postDirModel.getPostTime(), postDirModel.getContents(), url);
            postDirList.add(postDirDto);

        }
        Collections.reverse(postDirList);
        return Result.ok().data("PostDirList", postDirList);
    }
}
