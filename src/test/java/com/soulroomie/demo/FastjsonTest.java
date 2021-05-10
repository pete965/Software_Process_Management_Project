package com.soulroomie.demo;

import com.alibaba.fastjson.JSON;
import com.soulroomie.demo.beans.Apple;
import org.junit.jupiter.api.Test;

public class FastjsonTest {
    @Test
    public void testFastJson(){
        Apple apple = new Apple();
        apple.setName("apple-A");
        apple.setWeight(1);
        System.out.println(JSON.toJSONString(apple));
    }
}
