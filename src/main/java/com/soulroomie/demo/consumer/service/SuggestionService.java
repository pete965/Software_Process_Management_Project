package com.soulroomie.demo.consumer.service;

import com.soulroomie.demo.consumer.service.model.ExpectationModel;
import com.soulroomie.demo.consumer.service.model.UserInfoModel;
import com.soulroomie.demo.tools.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.soulroomie.demo.tools.Data.UNLIMITED;


@Service
public class SuggestionService {

    @Autowired
    ExpectationService expectationService;

    @Autowired
    UserInfoService userInfoService;

    public List<UserInfoModel> getSuggestionList(String userName) {
        List<UserInfoModel> infoModels = userInfoService.queryByUserNames(findSuggestedUserName(userName));
        return infoModels;
    }


    public List<String> findSuggestedUserName(String userName) {
        List<ExpectationModel> expectationModels = expectationService.listExpectation();
        ExpectationModel curExpectation = expectationService.selectByUserName(userName);
        List<String> useNames = new CopyOnWriteArrayList<>();
        if (curExpectation == null) {
            return useNames;
        }
        expectationModels.parallelStream().forEach((e) -> {
            if (isSuggested(curExpectation, e)) {
                useNames.add(e.getExpUserName());
            }
        });
        return useNames;
    }

    /**
     * follow the Data.class
     *
     * @param curExpectation
     * @param target
     * @return
     */
    public boolean isSuggested(ExpectationModel curExpectation, ExpectationModel target) {
        if (curExpectation.getExpUserName().equals(target.getExpUserName())) {
            return false;
        }

        int score = 0;

        if (curExpectation.getExpRoomType().equals(UNLIMITED) || target.getExpRoomType().equals(UNLIMITED)) {
            score += Data.roomTypeMap.get(UNLIMITED);
        } else {
            if (curExpectation.getExpRoomType().equals(target.getExpRoomType())) {
                score += Data.roomTypeMap.get(curExpectation.getExpRoomType());
            }
        }

        if (curExpectation.getExpBedroomNum().equals(UNLIMITED) || target.getExpBedroomNum().equals(UNLIMITED)) {
            score += Data.bedroomNumMap.get(UNLIMITED);
        } else {
            if (curExpectation.getExpBedroomNum().equals(target.getExpBedroomNum())) {
                score += Data.bedroomNumMap.get(curExpectation.getExpBedroomNum());
            }
        }

        if (curExpectation.getExpBathroomNum().equals(UNLIMITED) || target.getExpBathroomNum().equals(UNLIMITED)) {
            score += Data.bathroomNumMap.get(UNLIMITED);
        } else {
            if (curExpectation.getExpBathroomNum().equals(target.getExpBathroomNum())) {
                score += Data.bathroomNumMap.get(curExpectation.getExpBathroomNum());
            }
        }

        if (curExpectation.getExpBalcony().equals(UNLIMITED) || target.getExpBalcony().equals(UNLIMITED)) {
            score += Data.balconyMap.get(UNLIMITED);
        } else {
            if (curExpectation.getExpBalcony().equals(target.getExpBalcony())) {
                score += Data.balconyMap.get(curExpectation.getExpBalcony());
            }
        }

//        Date curInTime = DateTimeFormatterUtil.parseToDate(curExpectation.getExpIntime(), "YYYYMMdd");
//
//        Date curOutTime = DateTimeFormatterUtil.parseToDate(curExpectation.getExpIntime(), "YYYYMMdd");


        if (curExpectation.getExpDuration().equals(target.getExpDuration())) {
            score += Data.durationMap.get(curExpectation.getExpDuration());
        }


        if (curExpectation.getExpSex().equals(UNLIMITED) || target.getExpSex().equals(UNLIMITED)) {
            score += Data.sexMap.get(UNLIMITED);
        } else {
            if (curExpectation.getExpSex().equals(target.getExpSex())) {
                score += Data.sexMap.get(curExpectation.getExpSex());
            }
        }

        if (curExpectation.getExpJob().equals(UNLIMITED) || target.getExpJob().equals(UNLIMITED)) {
            score += Data.jobMap.get(UNLIMITED);
        } else {
            if (curExpectation.getExpJob().equals(target.getExpJob())) {
                score += Data.jobMap.get(curExpectation.getExpJob());
            }
        }

        if (curExpectation.getExpIfPet().equals(UNLIMITED) || target.getExpIfPet().equals(UNLIMITED)) {
            score += Data.petMap.get(UNLIMITED);
        } else {
            if (curExpectation.getExpIfPet().equals(target.getExpIfPet())) {
                score += Data.petMap.get(curExpectation.getExpIfPet());
            }
        }

        if (score > Data.THRESHOLD) {
            return true;
        }

        return false;
    }

}
