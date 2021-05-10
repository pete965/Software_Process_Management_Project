package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.service.mapper.CacheMapper;
import com.soulroomie.demo.consumer.service.model.CacheModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Local Cache Service
 */
@Service
public class CacheService {
    @Resource
    CacheMapper cacheMapper;

    // find all the lists
    public List<CacheModel> listAllCaches() {
        List<CacheModel> list = cacheMapper.selectList(null);
        return list;
    }

    // find by id
    public CacheModel listById() {
        return cacheMapper.selectById(1);
    }

    // find by some parameters
    public CacheModel findByTag() {
        String f2 = "banana";
        LambdaQueryWrapper<CacheModel> queryWrapper = new LambdaQueryWrapper<>();
        // use eq , ge ...
        queryWrapper.eq(CacheModel::getF2, f2);
        return cacheMapper.selectOne(queryWrapper);
    }

}
