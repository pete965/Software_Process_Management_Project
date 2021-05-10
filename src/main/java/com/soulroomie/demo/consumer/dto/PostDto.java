package com.soulroomie.demo.consumer.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PostDto {
    int id;
    String title;
    String userName;
    String postTime;
    String contents;
    String address;
    ArrayList<String> imageStrList;
}