package com.soulroomie.demo.consumer.controller;


import com.soulroomie.demo.consumer.dto.PostDto;
import com.soulroomie.demo.consumer.dto.ShowPostDto;
import com.soulroomie.demo.consumer.service.PostService;
import com.soulroomie.demo.consumer.service.model.ImageModel;
import com.soulroomie.demo.consumer.service.model.PostModel;
import com.soulroomie.demo.tools.ImageHelper;
import com.soulroomie.demo.tools.Result;
import com.soulroomie.demo.tools.http.HttpHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("userPost")
public class PostInformationController {

    @Resource
    PostService postService;

    @PostMapping("/createpost")
    @ResponseBody
    public Result createPostMessage(@RequestBody PostDto postDto) throws IOException {
        PostModel postModel = createPostModel(postDto);
        List<ImageModel> imageModels = createImageModel(postDto);
        return postService.storePost(postModel, imageModels);
    }

    private PostModel createPostModel(PostDto postDto) {
        return PostModel.builder().title(postDto.getTitle()).
                userName(postDto.getUserName()).
                postTime(postDto.getPostTime()).
                contents(postDto.getContents()).
                address(postDto.getAddress()).build();
    }

    private List<ImageModel> createImageModel(PostDto postDto) throws IOException {
        ArrayList<String> imageList = postDto.getImageStrList();
        List<ImageModel> imageModels = new ArrayList<>();
        HttpHelper httpHelper = new HttpHelper();

        if (imageList != null){
            for(String fileStr: imageList){
                byte[] fileByte = ImageHelper.string2Image(fileStr);
                String url = httpHelper.upload(fileByte);
                ImageModel imageModel = ImageModel.builder().imageUrl(url).build();
                imageModels.add(imageModel);
            }
        }
        return imageModels;
    }


    @PostMapping("/showPost")
    @ResponseBody
    public Result getPost(@RequestBody ShowPostDto showPostDto){
        PostModel postModel = PostModel.builder().id(showPostDto.getId()).build();
//        ImageModel imageModel = ImageModel.builder().id(showPostDto.getId()).build();
        return postService.showPost(postModel);
    }

}
