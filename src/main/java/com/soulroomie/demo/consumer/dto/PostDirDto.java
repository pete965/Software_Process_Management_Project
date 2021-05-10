package com.soulroomie.demo.consumer.dto;

import lombok.Data;

@Data
public class PostDirDto {
    private String id;
    private String userName;
    private String title;
    private String postTime;
    private String contents;
    private String url;

    public PostDirDto(String id, String userName, String title, String postTime, String contents, String url) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.postTime = postTime;
        this.contents = contents;
        this.url = url;
    }
}
