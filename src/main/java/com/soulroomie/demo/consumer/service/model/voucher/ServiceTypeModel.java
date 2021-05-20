package com.soulroomie.demo.consumer.service.model.voucher;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("service_type")
@Data
@Builder
public class ServiceTypeModel {
    private String name;
}
