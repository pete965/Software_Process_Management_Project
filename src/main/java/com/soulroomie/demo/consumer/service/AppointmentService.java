package com.soulroomie.demo.consumer.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.soulroomie.demo.consumer.dto.AppointmentDetailDto;
import com.soulroomie.demo.consumer.dto.AppointmentOperationDto;
import com.soulroomie.demo.consumer.service.mapper.AppointmentMapper;
import com.soulroomie.demo.consumer.service.mapper.AppointmentRequestMapper;
import com.soulroomie.demo.consumer.service.model.AppointmentModel;
import com.soulroomie.demo.consumer.service.model.AppointmentRequestModel;
import com.soulroomie.demo.tools.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppointmentService {
    @Resource
    AppointmentMapper appointmentMapper;
    @Resource
    AppointmentRequestMapper appointmentRequestMapper;

    private final String notExistMessage = "Not exist";
    private final String emptyMessage = "Empty Query";

    @Transactional
    public Result viewDetail(AppointmentDetailDto appointmentDetailDto) {
        LambdaQueryWrapper<AppointmentModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppointmentModel::getOwner,appointmentDetailDto.getOwner());
        AppointmentModel appointmentModel = appointmentMapper.selectOne(queryWrapper);
        if (appointmentModel == null){
            return Result.ok().message(notExistMessage);
        }else {
            return Result.ok().data("detail",appointmentModel);
        }
    }

    @Transactional
    public Result saveDetail(AppointmentModel appointmentModel) {
        if (appointmentModel == null){
            return Result.ok().message(emptyMessage);
        }
        LambdaQueryWrapper<AppointmentModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppointmentModel::getOwner,appointmentModel.getOwner());
        AppointmentModel exist = appointmentMapper.selectOne(queryWrapper);
        if (exist == null) {
            if (appointmentMapper.insert(appointmentModel) > 0){
                return Result.ok();
            }
            else return Result.error();
        }else {
            appointmentMapper.update(appointmentModel,queryWrapper);
            return Result.ok();
        }
    }

    @Transactional
    public Result viewList(AppointmentOperationDto appointmentOperationDto) {
        LambdaQueryWrapper<AppointmentRequestModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppointmentRequestModel::getReceiver,appointmentOperationDto.getReceiver());
        List<AppointmentRequestModel> appointmentRequestModels = appointmentRequestMapper.selectList(queryWrapper);
        if (appointmentRequestModels == null || appointmentRequestModels.size() == 0){
            return Result.ok().message(emptyMessage);
        }else {
            return Result.ok().data("list",appointmentRequestModels);
        }
    }

    @Transactional
    public Result operateList(AppointmentOperationDto appointmentOperationDto) {
        LambdaQueryWrapper<AppointmentRequestModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppointmentRequestModel::getReceiver,appointmentOperationDto.getReceiver());
        queryWrapper.eq(AppointmentRequestModel::getSender,appointmentOperationDto.getSender());
        AppointmentRequestModel appointmentRequestModel = appointmentRequestMapper.selectOne(queryWrapper);
        if (appointmentRequestModel == null) {
            return Result.ok().message(notExistMessage);
        }
        appointmentRequestModel.setStatus(appointmentOperationDto.getOperation());
        if (appointmentRequestMapper.update(appointmentRequestModel, queryWrapper) > 0)
            return Result.ok();
        else return Result.error();
    }

    @Transactional
    public Result addApplication(AppointmentRequestModel appointmentRequestModel) {
        LambdaQueryWrapper<AppointmentRequestModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppointmentRequestModel::getReceiver,appointmentRequestModel.getReceiver());
        queryWrapper.eq(AppointmentRequestModel::getSender,appointmentRequestModel.getSender());
        appointmentRequestModel.setStatus("new");
        if (appointmentRequestMapper.selectOne(queryWrapper) == null){
            if (appointmentRequestMapper.insert(appointmentRequestModel) != 0){
                return Result.ok();
            }else {
                return Result.error();
            }
        }else {
            return Result.ok();
        }
    }

    @Transactional
    public Result viewApplyStatus(AppointmentRequestModel appointmentRequestModel) {
        LambdaQueryWrapper<AppointmentRequestModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AppointmentRequestModel::getReceiver,appointmentRequestModel.getReceiver());
        queryWrapper.eq(AppointmentRequestModel::getSender,appointmentRequestModel.getSender());
        if (appointmentRequestMapper.selectOne(queryWrapper) == null){
            return Result.ok().message("true");
        }else {
            return Result.ok().message("false");
        }
    }
}
