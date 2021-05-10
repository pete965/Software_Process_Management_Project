package com.soulroomie.demo.consumer.service.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("expectation")
public class ExpectationModel {
    private Integer expId;
    private String expUserName;
    private String expRoomType;
    private String expBedroomNum;
    private String expBathroomNum;
    private String expBalcony;
    private String expDuration;
    private String expIntime;
    private String expOuttime;
    private String expSex;
    private String expAgeRange;
    private String expJob;
    private String expIfPet;


    public ExpectationModel(Integer expId,
                            String expUserName,
                            String expRoomType,
                            String expBedroomNum,
                            String expBathroomNum,
                            String expBalcony,
                            String expDuration,
                            String expIntime,
                            String expOuttime,
                            String expSex,
                            String expAgeRange,
                            String expJob,
                            String expIfPet) {
        this.expId = expId;
        this.expUserName = expUserName;
        this.expRoomType = expRoomType;
        this.expBedroomNum = expBedroomNum;
        this.expBathroomNum = expBathroomNum;
        this.expBalcony = expBalcony;
        this.expDuration = expDuration;
        this.expIntime = expIntime;
        this.expOuttime = expOuttime;
        this.expSex = expSex;
        this.expAgeRange = expAgeRange;
        this.expJob = expJob;
        this.expIfPet = expIfPet;
    }
}
