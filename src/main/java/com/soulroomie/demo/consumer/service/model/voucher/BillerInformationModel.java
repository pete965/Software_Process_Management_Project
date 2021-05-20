package com.soulroomie.demo.consumer.service.model.voucher;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@TableName("biller_information")
@Data
@Builder
public class BillerInformationModel {
    private String customer_email;
    private String name;
    private String biller_email;
}
