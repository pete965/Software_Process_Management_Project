package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soulroomie.demo.consumer.service.mapper.ExpectationMapper;
import com.soulroomie.demo.consumer.service.model.ExpectationModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExpectationService {
    @Resource
    private ExpectationMapper expectationMapper;
    public static final int NUM = 0;
    public static String message = "";


    @Transactional
    public Result addExpectationInformation(ExpectationModel expectation) {
        ExpectationModel existExpectation = queryExpectation(expectation);
        if (existExpectation != null) {
            message = "Expectation exist!";
            return Result.ok().message(message);
        }
        if (expectationMapper.insert(expectation) > NUM)
            return Result.ok();
        else return Result.error();
    }

    @Transactional
    public Result editExpectationInformation(ExpectationModel expectation) {
        LambdaQueryWrapper<ExpectationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExpectationModel::getExpUserName, expectation.getExpUserName());
        ExpectationModel existExpectation = expectationMapper.selectOne(queryWrapper);
        if (existExpectation == null) {
            message = "Expectation not exist!";
            return Result.ok().message(message);
        }
        if (expectationMapper.update(expectation, queryWrapper) > NUM)
            return Result.ok();
        else return Result.error();
    }

    @Transactional
    public Result viewExpectationInformation(ExpectationModel expectation) {
        ExpectationModel existExpectation = queryExpectation(expectation);
        if (existExpectation == null) {
            message = "Expectation not exist!";
            return Result.ok().message(message);
        }
//        return JSON.toJSONString(existExpectation);
        return Result.ok().data("Expectation", existExpectation);
    }


    @Transactional
    public Result deleteExpectationInformation(ExpectationModel expectation) {
        LambdaQueryWrapper<ExpectationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExpectationModel::getExpUserName, expectation.getExpUserName());
        ExpectationModel existExpectation = expectationMapper.selectOne(queryWrapper);
        if (existExpectation == null) {
            message = "Expectation not exist!";
            return Result.ok().message(message);
        }
        if (expectationMapper.delete(queryWrapper) > NUM)
            return Result.ok();
        else return Result.error();
    }

    public ExpectationModel queryExpectation(ExpectationModel expectation) {
        LambdaQueryWrapper<ExpectationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExpectationModel::getExpUserName, expectation.getExpUserName());
        ExpectationModel existExpectation = expectationMapper.selectOne(queryWrapper);
        return existExpectation;
    }


    public List<ExpectationModel> listExpectation() {
        QueryWrapper<ExpectationModel> queryWrapper = new QueryWrapper<>();
        return expectationMapper.selectList(queryWrapper);
    }

    public ExpectationModel selectByUserName(String userName) {
        LambdaQueryWrapper<ExpectationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExpectationModel::getExpUserName, userName);
        return expectationMapper.selectOne(queryWrapper);
    }
}
