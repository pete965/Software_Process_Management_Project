package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.soulroomie.demo.consumer.service.mapper.ImageMapper;
import com.soulroomie.demo.consumer.service.mapper.PostMapper;
import com.soulroomie.demo.consumer.service.model.ImageModel;
import com.soulroomie.demo.consumer.service.model.PostModel;
import com.soulroomie.demo.consumer.service.model.UserModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PostService {

    @Resource
    PostMapper postMapper;

    @Resource
    ImageMapper imageMapper;

//    @PostConstruct
//    void text(){
//        PostModel postModel = new PostModel();
//        postModel.setTitle("shaanxi");
//        ImageModel imageModel = new ImageModel();
//        imageModel.setImageUrl("http://1234");
//        this.storePost(postModel, imageModel);
//        System.out.println("finish");
//    }

    @Transactional
    public Result storePost(PostModel postModel, List<ImageModel> imageModels){
        postMapper.insert(postModel);
        int postId = postModel.getId();
        if (!imageModels.isEmpty()){
            for (ImageModel imageModel: imageModels) {
                imageModel.setPostId(postId);
                imageMapper.insert(imageModel);
            }
        }
        return Result.ok();
    }

    @Transactional
    public Result showPost(PostModel postModel) {
        int postId = postModel.getId();
        LambdaQueryWrapper<PostModel> postQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ImageModel> imageQueryWrapper = new LambdaQueryWrapper<>();

        postQueryWrapper.eq(PostModel::getId,postId);
        imageQueryWrapper.eq(ImageModel::getPostId, postId);
        List<PostModel> postList = postMapper.selectList(postQueryWrapper);
        List<ImageModel> imageList = imageMapper.selectList(imageQueryWrapper);
        if (postList.size() > 0){
            PostModel post = postList.get(0);
            Map<String, Object> postMap = getPost(post);
            if (imageList.size() > 0){
                List<String> urlList = new ArrayList<>();
                for (ImageModel imageModel: imageList){
                    urlList.add(imageModel.getImageUrl());
                }
                postMap.put("urlList", urlList);
            }else {
                postMap.put("urlList", null);
            }
            return Result.ok().data(postMap);
        }else {
            return Result.error();
        }
    }

    private Map<String, Object> getPost(PostModel post){
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("postId", post.getId()+"");
        postMap.put("userName", post.getUserName());
        postMap.put("title", post.getTitle());
        postMap.put("postTime", post.getPostTime());
        postMap.put("contents", post.getContents());
        postMap.put("address", post.getAddress());
        return postMap;
    }
}

